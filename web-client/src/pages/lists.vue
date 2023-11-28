<script lang="ts">
import { fetchShoppingLists } from '../typescript/api.ts'

export const useShoppingLists = defineLoader(
  '/lists',
  async () => {
    return await fetchShoppingLists()
  },
  { lazy: false }
)
</script>
<script lang="ts" setup>
import { ref } from 'vue'
import SidebarNav from '../components/SidebarNav.vue'
import NavHeader from '../components/NavHeader.vue'

const openSidebar = ref<boolean>(false)

const { data: lists, refresh } = useShoppingLists()

const router = useRouter()

if (useRoute().name === '/lists' && lists.value.length) {
  router.replace({
    name: '/lists/[id]',
    params: { id: lists.value[0].id.toString() }
  })
}

onBeforeRouteLeave((to, from) => {
  if (to.name === '/lists' && from.name === '/lists/[id]' && lists.value.length) {
    router.replace({
      name: '/lists/[id]',
      params: { id: lists.value[0].id.toString() }
    })
  }
})

definePage({
  alias: ['']
})
</script>
<template>
  <NavHeader :open="openSidebar" @open="openSidebar = true"></NavHeader>
  <SidebarNav :open="openSidebar" :lists="lists" @close="openSidebar = false" @update="refresh"></SidebarNav>

  <main
    class="mx-auto mt-[var(--header-height)] max-w-[var(--content-max-width)] pr-[var(--content-padding)] pt-[var(--content-padding)] md:pl-[calc(var(--sidebar-width)+var(--content-padding))]"
  >
    <router-view></router-view>
  </main>
</template>
