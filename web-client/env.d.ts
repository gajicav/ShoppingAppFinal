/// <reference types="vite/client" />

interface SLInput {
  description: string
}

interface SLResponse {
  id: number
  description: string
  count: number
}

interface SLItemInput {
  description: string
  quantity: number
  price: number
}

interface SLItemResponse {
  id: number
  description: string
  quantity: number
  price: number
  total: number
}
