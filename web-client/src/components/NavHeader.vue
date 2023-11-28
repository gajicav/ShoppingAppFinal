<script setup lang="ts">
import ThemeSwitcher from './ThemeSwitcher.vue'
import { Bars3Icon, MagnifyingGlassIcon } from '@heroicons/vue/24/outline'

defineProps<{
  open: boolean
}>()

defineEmits<{
  open: []
}>()
</script>

<template>
  <header
    class="fixed left-0 right-0 top-0 z-30 flex h-14 items-center overflow-hidden border-b border-zinc-200 bg-zinc-100 px-[var(--content-padding)] py-3 text-zinc-600 dark:border-zinc-750 dark:bg-zinc-900 dark:text-zinc-300"
  >
    <div
      class="mx-auto inline-flex w-full max-w-[var(--content-max-width)] items-center justify-between px-[var(--content-padding)]"
    >
      <div class="flex items-center">
        <button
          ref="menuBtn"
          class="mr-4 md:hidden"
          :aria-expanded="open"
          aria-controls="sidebar-nav"
          @click="$emit('open')"
        >
          <Bars3Icon class="h-6 w-6"></Bars3Icon>
          <span class="sr-only">Open Sidebar</span>
        </button>
        <a
          href="/"
          class="whitespace-nowrap text-xl font-semibold leading-snug text-zinc-900 dark:text-white"
        >
          Shopping List
        </a>
      </div>
      <nav class="hidden items-center md:flex">
        <div
          class="ml-6 flex items-center border-l border-zinc-200 pl-6 dark:border-zinc-750"
        >
          <ThemeSwitcher></ThemeSwitcher>
        </div>
      </nav>
    </div>
  </header>
</template>
