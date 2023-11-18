import { onBeforeUpdate, ref } from 'vue'

const shoppingLists = ref<ShoppingList[]>([])

const updateLists = async () => {
  try {
    const response = await fetch('/api/lists')
    shoppingLists.value = await response.json()
  } catch (e) {
    console.error(e)
  }
}

onBeforeUpdate(

)

export const getShoppingLists = () => shoppingLists
