/** @type {import('tailwindcss').Config} */
export default {
  darkMode: 'class',
  content: ['./index.html', './src/**/*.{vue,js,ts}'],
  theme: {
    extend: {
      colors: {
        zinc: {
          700: '#39393F',
          750: '#303035',
          800: '#2B2B2F',
          850: '#202023',
          900: '#1C1C1F'
        }
      }
    }
  }
}
