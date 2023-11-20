export async function fetchShoppingLists(): Promise<SLResponse[]> {
  const result = await fetch('/api/lists')
  return await extractCollection(result, 'lists')
}

export async function fetchShoppingList(id: string): Promise<SLResponse> {
  const list = await fetch(`/api/lists/${id}`)
  return await extractSingle(list)
}

export async function createShoppingList(list: SLInput): Promise<SLResponse> {
  const result = await fetch(`/api/lists`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(list)
  })
  return await extractSingle(result)
}

export async function fetchListItems(id: string): Promise<SLItemResponse[]> {
  const result = await fetch(`/api/lists/${id}/items`)
  return await extractCollection(result, 'items')
}

export async function fetchListItem(id: string): Promise<SLItemResponse> {
  const result = await fetch(`/api/items/${id}`)
  return await extractSingle(result)
}

async function extractJsonOrError(result: Response) {
  if (!result.ok) throw new Error('Network response was not OK')

  return await result.json()
}

export async function extractCollection(result: Response, propName: string) {
  try {
    return await extractJsonOrError(result).then(
      (res) => res['_embedded'][propName]
    )
  } catch (e) {
    console.error('Failed to extract collection from json response', e)
  }

  return []
}

export async function extractSingle(result: Response) {
  return await extractJsonOrError(result)
    .catch((e) =>
      console.error('Failed to extract object from json response', e)
    )
    .then(undefined, () => [])
}
