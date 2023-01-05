import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "@/assets/css/global.css";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import "@element-plus/icons-vue";
import axios from "axios";
import VueAxios from "vue-axios";
import { apiUrl } from "@/services/ApiService";

const app = createApp(App);
app.use(router);
app.use(ElementPlus);

//resolve the issue of session id not the same
axios.defaults.withCredentials = true;
axios.defaults.baseURL = apiUrl;

app.use(VueAxios, axios);
app.mount("#app");
