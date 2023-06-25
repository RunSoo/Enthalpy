import Vue from "vue";
import Vuetify from "vuetify/lib/framework";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: '#b3b3b3', // Set the primary button color
        secondary: '#00ff00', // Set the secondary button color
        accent: '#0000ff', // Set the accent button color
      },
    },
  },
});
