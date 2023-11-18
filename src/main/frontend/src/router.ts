import { createRouter, createWebHistory } from 'vue-router'

const ShoppingListView = () => import('./pages/ShoppingListView.vue')

export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/:listId', name: 'list', component: ShoppingListView },
    { path: '/', name: 'lists', component: ShoppingListView },
  ]
})
