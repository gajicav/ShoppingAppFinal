import prettierPlugin from 'eslint-plugin-prettier'
import vuePlugin from 'eslint-plugin-vue'
import * as espree from 'espree'
import tsParser from '@typescript-eslint/parser'
import tsPlugin from '@typescript-eslint/eslint-plugin'
import vueParser from 'vue-eslint-parser'
import globals from 'globals'
import js from '@eslint/js'

tsPlugin.configs
/** @type {import('eslint').Linter.FlatConfig[]} */
export default [
  vuePlugin.configs['flat/vue3-recommended'],
  {
    plugins: {
      prettier: prettierPlugin
    },
    rules: {
      'prettier/prettier': 'error',
      'arrow-body-style': 'off',
      'prefer-arrow-callback': 'off'
    }
  },
  {
    files: ['./*.js'],
    rules: js.configs.recommended.rules,
    languageOptions: {
      globals: globals.node
    }
  },
  {
    files: ['**/*.{js,ts,vue}'],
    languageOptions: {
      ecmaVersion: 2020,
      sourceType: 'module'
    }
  },
  {
    files: ['src/**/*.ts'],
    plugins: {
      '@typescript-eslint': tsPlugin
    },
    languageOptions: {
      parser: tsParser,
      globals: globals.browser,
      parserOptions: {
        globals: globals.node,
        sourceType: 'module',
        ecmaVersion: 2020
      }
    }
  },
  {
    files: ['./*.ts'],
    plugins: {
      '@typescript-eslint': tsPlugin
    },
    languageOptions: {
      parser: tsParser,
      globals: globals.node,
      parserOptions: {
        project: './tsconfig.node.json',
        sourceType: 'module',
        ecmaVersion: 'latest'
      }
    }
  },
  {
    files: ['src/**/*.vue'],
    plugins: {
      vue: vuePlugin
    },
    languageOptions: {
      parser: vueParser,
      parserOptions: {
        parser: {
          js: espree,
          ts: tsParser
        },
        sourceType: 'module',
        ecmaVersion: 2020
      }
    }
  }
]
