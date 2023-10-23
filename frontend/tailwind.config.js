/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.tsx"],
  theme: {
    extend: {
      colors: {
        background: "#09090A",
      },
      gridTemplateRows: {
        7: "repeat(7, minmax(0, 1fr))",
      },
      fontFamily: {
        'inter': ['Inter', 'sans-serif']
      }
    },
  },
  plugins: [
    require("@tailwindcss/forms")
  ],
}

