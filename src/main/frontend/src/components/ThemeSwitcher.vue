<script setup lang="ts">
import { SunIcon } from '@heroicons/vue/24/outline'
import { MoonIcon } from '@heroicons/vue/24/outline'
import { ComputerDesktopIcon } from '@heroicons/vue/24/outline'
import { Listbox, ListboxButton, ListboxLabel, ListboxOption, ListboxOptions } from '@headlessui/vue'
import { ref, watch } from 'vue'

const setTheme = (isDark: boolean) => {
  if (isDark) {
    document.documentElement.classList.add('dark')
  } else {
    document.documentElement.classList.remove('dark')
  }
}

const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)')

const currentTheme = ref<string | undefined>(localStorage.theme)

watch(
  currentTheme,
  (theme) => {
    if (theme === 'light' || theme === 'dark') {
      localStorage.theme = theme
      setTheme(theme === 'dark')
    } else {
      localStorage.removeItem('theme')
      setTheme(mediaQuery.matches)
    }
  },
  { immediate: true, flush: 'post' }
)

mediaQuery.addEventListener('change', () => {
  if (!('theme' in localStorage)) {
    setTheme(mediaQuery.matches)
  }
})

const themes = [
  { label: 'Light', mode: 'light', icon: SunIcon },
  { label: 'Dark', mode: 'dark', icon: MoonIcon },
  { label: 'System', mode: undefined, icon: ComputerDesktopIcon }
]
</script>

<template>
  <Listbox v-model="currentTheme">
    <ListboxLabel class="sr-only">Theme</ListboxLabel>
    <ListboxButton>
      <span class="dark:hidden">
        <SunIcon
          class="h-6 w-6"
          :class="[currentTheme ? 'stroke-blue-700 dark:stroke-blue-300' : 'stroke-zinc-700 dark:stroke-zinc-300']"
        />
      </span>
      <span class="hidden dark:inline">
        <MoonIcon
          class="h-6 w-6"
          :class="[currentTheme ? 'stroke-blue-700 dark:stroke-blue-300' : 'stroke-zinc-700 dark:stroke-zinc-300']"
        />
      </span>
    </ListboxButton>
    <ListboxOptions
      class="absolute right-2 top-16 z-50 w-36 overflow-hidden rounded-xl bg-zinc-100 py-1 text-sm font-semibold text-zinc-700 shadow-lg ring-1 ring-zinc-900/10 dark:bg-zinc-900 dark:text-zinc-200 dark:ring-white/10"
    >
      <ListboxOption v-for="(theme, index) in themes" v-slot="{ active, selected }" :key="index" :value="theme.mode">
        <li
          class="mx-2 my-1 flex cursor-pointer items-center rounded-lg px-2 py-1"
          :class="{
            'text-blue-500': selected,
            'bg-zinc-300/50 dark:bg-zinc-700/50': active
          }"
        >
          <component :is="theme.icon" class="mr-2 h-6 w-6"></component>
          {{ theme.label }}
        </li>
      </ListboxOption>
    </ListboxOptions>
  </Listbox>
</template>
