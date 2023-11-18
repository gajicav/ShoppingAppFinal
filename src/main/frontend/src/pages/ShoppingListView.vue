<script lang="ts" setup>
import { onBeforeMount, onBeforeUpdate, ref, watch } from 'vue'
import { PlusIcon } from '@heroicons/vue/24/outline'
import SidebarNav from '../components/SidebarNav.vue'
import NavHeader from '../components/NavHeader.vue'
import { createRouter, onBeforeRouteUpdate, useRouter } from 'vue-router'
const props = defineProps<{
  listId?: string
}>()

const USD = ref(
  new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
  })
)

const openSidebar = ref<boolean>(false)

const shoppingLists = ref<ShoppingList[]>([])

onBeforeUpdate(async () => {
  try {
    const response = await fetch('/api/lists')
    shoppingLists.value = await response.json()
  } catch (e) {
    console.error(e)
  }
})

const items = ref<ShoppingListItem[]>([])

const loading = ref<boolean>(false)

const router = useRouter()

watch(
  () => props.listId,
  async (id) => {
    loading.value = true

    if (!id && shoppingLists.value?.length) id = router.replace({ path: '/list', params: shoppingLists.value[0].id })

    if (!id) return

    await fetch(`/api/lists/${id}/items`, { method: 'GET' })
      .then((response) => response.json())
      .then((data) => (items.value = data))
      .catch(console.error)

    loading.value = false
  },
  { immediate: true, flush: 'post' }
)
</script>
<template>
  <NavHeader :open="openSidebar" @open="openSidebar = true"></NavHeader>
  <SidebarNav :open="openSidebar" :lists="shoppingLists" @close="openSidebar = false"></SidebarNav>

  <main class="mt-[var(--header-height)] h-full p-[var(--content-padding)]">
    <div class="mx-auto max-w-[var(--content-max-width)] md:pl-[calc(var(--sidebar-width)+var(--content-padding))]">
      <Transition>
        <div v-if="loading" role="status" class="fixed inset-0 z-10 bg-zinc-900/10 dark:bg-zinc-900/60">
          <span class="sr-only">Loading content</span>
        </div>
      </Transition>

      <ul class="grid grid-cols-1 gap-x-6 gap-y-4 lg:grid-cols-2 xl:grid-cols-3">
        <li
          v-for="item in items"
          :key="item.name"
          class="rounded-xl border border-zinc-200 bg-zinc-100 shadow-md dark:border-zinc-750 dark:bg-zinc-800"
        >
          <div class="p-4">
            <h5 class="mb-2 text-lg font-semibold tracking-tight text-zinc-900 dark:text-white">
              {{ item.name }} ({{ item.quantity }})
            </h5>
            <p class="text-sm font-normal text-zinc-700 dark:text-zinc-400">
              {{ USD.format(item.price) }}
            </p>
          </div>
        </li>
        <li>
          <a
            class="flex h-full items-center justify-center rounded-xl border border-blue-200 bg-blue-300/30 text-blue-700 shadow-md hover:bg-blue-300/40 dark:border-blue-600/10 dark:bg-blue-700/20 dark:text-blue-300 hover:dark:bg-blue-700/25"
          >
            <span class="sr-only">Add item</span>
            <PlusIcon class="h-12 w-12"></PlusIcon>
          </a>
        </li>
      </ul>
    </div>
  </main>
</template>
