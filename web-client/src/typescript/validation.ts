import type { Rules } from 'async-validator/dist-types'

export const listRules: Rules = {
  description: {
    type: 'string',
    min: 1,
    max: 100,
    required: true,
    transform(value) {
      return value.trim()
    }
  }
}

export const itemRules: Rules = {
  description: {
    type: 'string',
    min: 1,
    max: 100,
    required: true,
    transform(value) {
      return value.trim()
    }
  },
  price: {
    type: 'float',
    min: 0,
    max: 999999.99,
    required: true,
    transform(value) {
      return value.toFixed(2)
    }
  },
  quantity: {
    type: 'integer',
    min: 1,
    max: 9999,
    required: true
  }
}
