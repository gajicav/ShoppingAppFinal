<script lang="ts">
import { fetchShoppingLists } from '../typescript/api.ts'

export const useShoppingLists = defineLoader(
  async (): Promise<SLResponse[]> => {
    return await fetchShoppingLists()
  }
)
</script>
<script lang="ts" setup>
import { ref } from 'vue'
import SidebarNav from '../components/SidebarNav.vue'
import NavHeader from '../components/NavHeader.vue'

const openSidebar = ref<boolean>(false)

const { data: shoppingLists, refresh } = useShoppingLists()

if (shoppingLists.value.length) {
  useRouter().replace('/lists/' + shoppingLists.value[0].id.toString())
}

definePage({
  alias: ['']
})
</script>
<template>
  <NavHeader :open="openSidebar" @open="openSidebar = true"></NavHeader>
  <SidebarNav
    :open="openSidebar"
    :lists="shoppingLists"
    @close="openSidebar = false"
    @created-list="refresh"
  ></SidebarNav>

  <main class="mt-[var(--header-height)] h-full p-[var(--content-padding)]">
    <div
      class="mx-auto max-w-[var(--content-max-width)] md:pl-[calc(var(--sidebar-width)+var(--content-padding))]"
    >
      <router-view></router-view>
    </div>
  </main>
</template>
