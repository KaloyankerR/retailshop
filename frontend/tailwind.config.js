/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
    "./node_modules/flowbite/**/*.js",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          light: '#6ec1e4',
          DEFAULT: '#2196f3',
          dark: '#1e88e5',
        },
      },
    },
  },
  plugins: [
    require('flowbite/plugin'),
    
  ],
}

