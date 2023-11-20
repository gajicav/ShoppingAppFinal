<script lang="ts">
import { fetchListItems } from '../../typescript/api.ts'
import { defineLoader } from 'vue-router/auto'
export const useListItems = defineLoader(async (route) => {
  return await fetchListItems(route.params.id)
})
</script>

<script setup lang="ts">
import { PlusIcon } from '@heroicons/vue/24/outline'
import { ref } from 'vue'

const USD = ref(
  new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
  })
)

const { data: items, pending } = useListItems()
</script>

<template>
  <Transition>
    <div
      v-if="pending"
      role="status"
      class="fixed inset-0 z-10 bg-zinc-900/10 dark:bg-zinc-900/60"
    >
      <span class="sr-only">Loading content</span>
    </div>
  </Transition>

  <ul
    class="grid grid-cols-1 gap-x-6 gap-y-4 lg:grid-cols-2 xl:grid-cols-3"
    v-if="items"
  >
    <li
      v-for="item in items"
      :key="item.id"
      class="rounded-xl border border-zinc-200 bg-zinc-100 shadow-md dark:border-zinc-750 dark:bg-zinc-800"
    >
      <div class="p-4">
        <h5
          class="mb-2 text-lg font-semibold tracking-tight text-zinc-900 dark:text-white"
        >
          {{ item.description }} ({{ item.quantity }})
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
</template>
