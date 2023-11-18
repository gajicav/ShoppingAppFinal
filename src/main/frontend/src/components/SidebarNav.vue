<script setup lang="ts">
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const props = defineProps<{
  open: boolean
  lists: ShoppingList[]
}>()

const emit = defineEmits<{
  (e: 'close'): void
}>()

const sidebar = ref<HTMLElement | null>(null)

const onEscape = (e: KeyboardEvent) => {
  if (e.key === 'Escape') emit('close')
}

let menuBtn: HTMLElement | null = null

watch(
  () => props.open,
  (open: boolean) => {
    if (open) {
      menuBtn = document.activeElement as HTMLElement
      document.documentElement.classList.add('overflow-hidden')
      sidebar.value?.focus()
      window.addEventListener('keyup', onEscape)
    } else {
      document.documentElement.classList.remove('overflow-hidden')
      menuBtn?.focus()
      window.removeEventListener('keyup', onEscape)
    }
  },
  { flush: 'post' }
)

const route = useRoute()
watch(
  () => route.path,
  () => emit('close')
)
</script>

<template>
  <Transition name="fade" enter-from-class="opacity-0" leave-to-class="opacity-0" leave-active-class="duration-150">
    <div
      v-if="open"
      class="fixed inset-0 z-40 bg-zinc-900/30 transition-opacity duration-500 dark:bg-zinc-900/80 md:hidden"
      aria-hidden="true"
      @click="$emit('close')"
    />
  </Transition>

  <aside id="sidebar-nav" ref="sidebar" class="sidebar" :class="{ open }">
    <nav aria-labelledby="sidebar-label">
      <h5
        id="sidebar-label"
        class="border-b border-zinc-200 pb-2 font-semibold leading-snug text-zinc-500 dark:border-zinc-800 dark:text-zinc-400"
      >
        Shopping Lists
      </h5>
      <ul class="overflow-y-auto font-semibold">
        <li v-for="list in lists" :key="list.id" class="mt-2">
          <router-link
            :to="{ name: 'list', params: { listId: list.id.toString() } }"
            class="flex items-center justify-between rounded-lg px-3 py-2 hover:bg-zinc-200 dark:hover:bg-zinc-800"
            exact-active-class="bg-zinc-200 dark:bg-zinc-800 hover:bg-zinc-300 dark:hover:bg-zinc-750"
          >
            <span class="overflow-hidden overflow-ellipsis whitespace-nowrap">{{ list.name }}</span>
            <span
              class="ml-3 inline-flex h-3 w-3 items-center justify-center rounded-full bg-blue-400/60 p-3 text-sm text-blue-700 dark:bg-blue-600/60 dark:text-blue-200"
              >{{ list.size }}</span
            >
          </router-link>
        </li>
        <li>
          <router-link
            :to="{ name: 'list', params: { listId: list.id.toString() } }"
            class="flex items-center justify-between rounded-lg px-3 py-2 hover:bg-zinc-200 dark:hover:bg-zinc-800"
            exact-active-class="bg-zinc-200 dark:bg-zinc-800 hover:bg-zinc-300 dark:hover:bg-zinc-750"
          >
            <form action="/api/lists" method="post">
              <input v-model="name" placeholder="list name" />
            </form>
            <span class="overflow-hidden overflow-ellipsis whitespace-nowrap">{{ list.name }}</span>
            <span
              class="ml-3 inline-flex h-3 w-3 items-center justify-center rounded-full bg-blue-400/60 p-3 text-sm text-blue-700 dark:bg-blue-600/60 dark:text-blue-200"
            >{{ list.size }}</span
            >
          </router-link>
        </li>
      </ul>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  @apply z-20 border-r border-zinc-200 dark:border-zinc-750;
  width: var(--sidebar-width);
  top: var(--header-height);
  left: max(var(--content-padding), calc(50% - var(--content-max-width) / 2));
  right: auto;
  bottom: 0;
  position: fixed;
  transition:
    opacity 500ms,
    transform 200ms ease;
  padding: 1em var(--content-padding) 1em 0;

  @media not all and (min-width: theme('screens.md')) {
    @apply bg-zinc-100 dark:bg-zinc-900;
    opacity: 0;
    transform: translateX(-100%);

    &.open {
      @apply z-50;
      top: 0;
      left: 0;
      opacity: 100%;
      transform: translateX(0);
      transition:
        opacity 200ms,
        transform 300ms cubic-bezier(0.15, 0.1, 0.25, 1);
      padding: var(--content-padding);
    }
  }
}
</style>
