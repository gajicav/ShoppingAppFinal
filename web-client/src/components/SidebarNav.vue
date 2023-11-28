<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { PlusIcon } from '@heroicons/vue/24/outline'
import { useAsyncValidator } from '@vueuse/integrations/useAsyncValidator'
import { listRules } from '../typescript/validation.ts'
import { createShoppingList } from '../typescript/api.ts'

const props = defineProps<{
  open: boolean
  lists: SLResponse[]
}>()

const emit = defineEmits<{
  close: []
  update: []
}>()

const sidebar = ref<HTMLElement | null>(null)

function onEscape(e: KeyboardEvent) {
  if (e.key === 'Escape') {
    emit('close')
  }
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
  }
)

const route = useRoute()
watch(
  () => route.path,
  () => emit('close')
)

const router = useRouter()

async function doSubmit(list: SLInput) {
  const created = await createShoppingList(list)

  if (created) {
    emit('update')
    router.replace({
      name: '/lists/[id]',
      params: { id: created.id.toString() }
    })
  }
}

const form: SLInput = reactive({ description: '' })

const { pass } = useAsyncValidator(form, listRules)
</script>

<template>
  <Transition
    name="fade"
    enter-from-class="opacity-0"
    leave-to-class="opacity-0"
    leave-active-class="duration-150"
  >
    <div
      v-if="open"
      class="fixed inset-0 z-40 bg-zinc-900/30 transition-opacity duration-500 dark:bg-zinc-900/80 md:hidden"
      aria-hidden="true"
      @click="$emit('close')"
    />
  </Transition>

  <aside
    id="sidebar-nav"
    ref="sidebar"
    class="sidebar overflow-hidden"
    :class="{ open: open }"
  >
    <section class="mt-[var(--content-padding)] px-[var(--content-padding)]">
      <h5
        class="border-b border-zinc-200 pb-2.5 font-semibold leading-snug text-zinc-500 dark:border-zinc-800 dark:text-zinc-400"
      >
        Add List
      </h5>
      <div class="flex items-center pt-3">
        <input
          v-model="form.description"
          class="block h-8 min-w-0 flex-grow rounded-full border border-zinc-300 bg-zinc-200 px-4 text-sm focus:border-blue-300 focus:outline-none focus:ring-1 focus:ring-blue-300 dark:border-zinc-750 dark:bg-zinc-800 dark:focus:border-blue-500 dark:focus:ring-blue-500"
          type="text"
          placeholder="Description"
        />
        <button
          type="button"
          :disabled="!pass"
          @click="doSubmit(form)"
          class="ml-3 flex h-8 w-8 items-center justify-center rounded-full bg-blue-400/60 text-blue-700 hover:bg-blue-400/75 disabled:opacity-50 dark:bg-blue-600/60 dark:text-blue-200 hover:dark:dark:bg-blue-600/75"
        >
          <span class="sr-only">Submit List</span>
          <PlusIcon class="h-5 w-5"></PlusIcon>
        </button>
      </div>
    </section>
    <nav
      aria-labelledby="sidebar-label"
      class="mt-[var(--content-padding)] overflow-y-scroll px-[var(--content-padding)]"
      v-if="lists.length"
    >
      <h5
        id="sidebar-label"
        class="border-b border-zinc-200 pb-2.5 font-semibold leading-snug text-zinc-500 dark:border-zinc-800 dark:text-zinc-400"
      >
        Shopping Lists
      </h5>
      <div class="max-h-full">
        <ul class="pt-3 font-semibold">
          <li v-for="list in lists" :key="list.id" class="mb-2">
            <router-link
              :to="{ name: '/lists/[id]', params: { id: list.id.toString() } }"
              class="flex items-center justify-between rounded-lg px-3 py-2 hover:bg-zinc-200 dark:hover:bg-zinc-800"
              exact-active-class="bg-zinc-200 dark:bg-zinc-800 hover:bg-zinc-300 dark:hover:bg-zinc-750"
            >
              <span
                class="overflow-hidden overflow-ellipsis whitespace-nowrap"
                >{{ list.description }}</span
              >
              <span
                class="ml-3 inline-flex h-3 w-3 items-center justify-center rounded-full bg-blue-400/60 p-3 text-sm text-blue-700 dark:bg-blue-600/60 dark:text-blue-200"
                aria-label="list size"
                >{{ list.count }}</span
              >
            </router-link>
          </li>
        </ul>
      </div>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  @apply z-20 flex max-h-full flex-col border-r border-zinc-200 dark:border-zinc-750;
  width: var(--sidebar-width);
  top: var(--header-height);
  left: max(0px, calc(50% - var(--content-max-width) / 2));
  right: auto;
  bottom: 0;
  position: fixed;
  transition:
    opacity 500ms,
    transform 200ms ease;

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
    }
  }
}
</style>
