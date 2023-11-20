import prettierConfig from 'eslint-config-prettier'
import vuePlugin from 'eslint-plugin-vue'
import * as espree from 'espree'
import tsParser from '@typescript-eslint/parser'
import tsPlugin from '@typescript-eslint/eslint-plugin'
import vueParser from 'vue-eslint-parser'
import globals from 'globals'
import js from '@eslint/js'

/** @type {import('eslint').Linter.FlatConfig[]} */
export default [
  {
    files: ['**/*.js'],
    rules: js.configs.recommended.rules,
    languageOptions: {
      globals: globals.node,
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
      globals: {
        ...globals.node,
        ...globals.browser
      },
      parser: tsParser,
      parserOptions: {
        project: 'tsconfig.json'
      }
    }
  },
  {
    files: ['vite.config.ts'],
    plugins: {
      '@typescript-eslint': tsPlugin
    },
    languageOptions: {
      parser: tsParser,
      globals: globals.node,
      parserOptions: {
        project: 'tsconfig.node.json'
      }
    }
  },
  {
    files: ['src/**/*.vue'],
    plugins: {
      vue: vuePlugin
    },
    languageOptions: {
      globals: {
        ...globals.node,
        ...globals.browser
      },
      sourceType: 'module',
      ecmaVersion: 2020,
      parser: vueParser,
      parserOptions: {
        parser: {
          js: espree,
          ts: tsParser
        }
      }
    }
  },
  prettierConfig
]
