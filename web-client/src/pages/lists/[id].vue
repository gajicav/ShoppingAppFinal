<script lang="ts">
import { fetchShoppingList, fetchListItems } from '../../typescript/api.ts'

export const useShoppingList = defineLoader('/lists/[id]', async (route) => {
  const items = await fetchListItems(route.params.id)
  const list = await fetchShoppingList(route.params.id)

  return { list: list, items: items }
})
</script>

<script setup lang="ts">
import { PlusIcon } from '@heroicons/vue/20/solid'
import { PencilSquareIcon } from '@heroicons/vue/20/solid'
import { TrashIcon } from '@heroicons/vue/20/solid'
import { deleteListItem, deleteShoppingList } from '../../typescript/api.ts'

import { ref } from 'vue'

const USD = ref(
  new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
  })
)

const { data: shoppingList, pending } = useShoppingList()

const router = useRouter()

if (!shoppingList.value.list) {
  router.replace({ name: '/lists' })
}
</script>

<template>
  <Transition>
    <div
      v-if="pending"
      role="status"
      class="fixed inset-0 z-10 bg-zinc-900/5 dark:bg-zinc-900/60"
    >
      <span class="sr-only">Loading content</span>
    </div>
  </Transition>

  <div class="flex flex-row justify-between items-end mb-6">
    <button
      class="flex items-center justify-center rounded-full border border-green-200 bg-green-300/30 px-4 py-2 text-green-700 hover:bg-green-300/40 dark:border-green-600/10 dark:bg-green-700/20 dark:text-green-300 hover:dark:bg-green-700/25"
      type="button"
    >
      <PlusIcon class="mr-2 h-5 w-5"></PlusIcon>
      Add item
    </button>


    <div class="text-4xl font-light leading-snug">
      {{ shoppingList.list.description }}
    </div>

    <div class="flex flex-row">
      <button
        class="flex items-center mr-2 justify-center rounded-full border border-blue-200 bg-blue-300/30 px-4 py-2 text-blue-700 hover:bg-blue-300/40 dark:border-blue-600/10 dark:bg-blue-700/20 dark:text-blue-300 hover:dark:bg-blue-700/25"
        type="button"
      >
        <PencilSquareIcon class="mr-2 h-5 w-5"></PencilSquareIcon>
        Rename
      </button>

      <button
        class="flex items-center justify-center rounded-full border border-red-200 bg-red-300/30 px-4 py-2 text-red-700 hover:bg-red-300/40 dark:border-red-600/10 dark:bg-red-700/20 dark:text-red-300 hover:dark:bg-red-700/25"
        type="button"
        @click="deleteShoppingList(shoppingList.list.id.toString())"
      >
        <TrashIcon class="mr-2 h-5 w-5"></TrashIcon>
        Delete
      </button>
    </div>
  </div>

  <router-view name="/lists/[id]/edit"></router-view>

  <ul
    class="grid grid-cols-1 gap-x-6 gap-y-4 lg:grid-cols-2 xl:grid-cols-3"
    v-if="shoppingList.items"
  >
    <li
      v-for="item in shoppingList.items"
      :key="item.id"
      class="rounded-2xl border border-zinc-200 bg-zinc-100 shadow-md dark:border-zinc-750 dark:bg-zinc-800 p-4 text-zinc-700 font-medium dark:text-zinc-400 text-sm"
    >
      <div class="grid grid-cols-3 grid-rows-2 gap-3 text-sm">
        <span class="font-semibold overflow-ellipsis leading-snug text-lg text-zinc-800 dark:text-zinc-300">
          {{ item.description }}
        </span>
        <span class="leading-snug">
          Quantity: <span class="font-semibold text-md">{{ item.quantity }}</span>
        </span>
        <button
          class="flex items-center justify-center rounded-full border border-blue-200 bg-blue-300/30 p-2 text-blue-700 hover:bg-blue-300/40 dark:border-blue-600/10 dark:bg-blue-700/20 dark:text-blue-300 hover:dark:bg-blue-700/25"
          type="button"
        >
          <PencilSquareIcon class="h-5 w-5"></PencilSquareIcon>
        </button>
        <span class="leading-snug">
          Price: <span class="font-semibold text-md">{{ USD.format(item.price) }}</span>
        </span>
        <span class="leading-snug">
          Total: <span class="font-semibold text-md">{{ USD.format(item.total) }}</span>
        </span>
        <button
          class="flex items-center justify-center rounded-full border border-red-200 bg-red-300/30 p-2 text-red-700 hover:bg-red-300/40 dark:border-red-600/10 dark:bg-red-700/20 dark:text-red-300 hover:dark:bg-red-700/25"
          type="button"
          @click="deleteListItem(item.id.toString())"
        >
          <TrashIcon class="h-5 w-5"></TrashIcon>
        </button>
      </div>
    </li>
  </ul>
</template>
