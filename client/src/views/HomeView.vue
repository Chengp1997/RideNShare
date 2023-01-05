<template>
  <div class="login-floater" id="loginFl">
    <div>
      <el-icon class="cross" size="20px" color="black" @click="backToHome"
        ><CircleClose
      /></el-icon>
    </div>
    <login-floater v-if="activateFloater === 1"></login-floater>
    <register-floater v-if="activateFloater === 2"></register-floater>
  </div>
  <div v-bind:class="{ mask: isMask }">
    <div class="container">
      <video class="video" id="v1" autoPlay loop muted>
        <source src="@/assets/images/background-video.mp4" type="video/mp4" />
      </video>

      <div>
        <img src="@/assets/images/mycarlogo2.png" alt="car logo" class="logo" />
      </div>
      <span class="headSpan" />

      <h1 class="homeStatement">
        <p>Premium Riding & Driving Experience</p>
      </h1>

      <h2 class="homeStatement">Take a Ride with a Hokie</h2>

      <div class="allButton">
        <button class="homeButton" @click="loginClick">Login</button>
        <button class="homeButton" @click="signupClick">Signup</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import LoginFloater from "@/components/LoginFloater.vue";
import RegisterFloater from "@/components/RegisterFloater.vue";
import { CircleClose } from "@element-plus/icons-vue";

export default defineComponent({
  name: "HomeView",
  data() {
    return {
      isMask: false,
      activateFloater: 0,
    };
  },
  computed: {},
  created() {
    this.$emit("header", false);
  },
  methods: {
    backToHome() {
      if (this.activateFloater != 0) {
        this.activateFloater = 0;
        const ph = document.getElementById("loginFl");
        if (ph != null) {
          ph.classList.remove("animationcss");
          // ph.classList.add("animationcss-reverse");
          // setTimeout(, 50);
          ph.classList.remove("activate");
        }
        this.isMask = false;
      }
    },
    loginClick() {
      this.activateFloater = 1;
      this.isMask = true;
      const ph = document.getElementById("loginFl");
      if (ph != null) {
        ph.classList.add("animationcss");
        ph.classList.add("activate");
      }
    },
    signupClick() {
      this.activateFloater = 2;
      this.isMask = true;
      const ph = document.getElementById("loginFl");
      if (ph != null) {
        ph.classList.add("animationcss");
        ph.classList.add("activate");
      }
    },
  },
  components: {
    RegisterFloater,
    LoginFloater,
    CircleClose,
  },
});
</script>

<style scoped>
.container {
  padding: 0 2rem;
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;
}

.headSpan {
  width: 1000px;
  height: 80px;
}
.cross {
  right: 1%;
  margin: 5px 0 5px 0;
  width: 20px;
  height: 20px;
  position: absolute;
  transition: All 0.2s ease-in-out;
}
.cross:hover {
  cursor: pointer;
  transform: rotate(-90deg);
}

.center {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.allButton {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
}

.main {
  min-height: 100vh;
  padding: 4rem 0;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.login-floater {
  margin: 10% 37%;
  visibility: hidden;
  position: fixed;
  background-color: white;
  width: 400px;
  height: 360px;
  z-index: 1;
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  /*opacity: 1;*/
  /*-webkit-transform: scale3d(1, 1, 1);*/
}
.placeholder {
  margin-left: 94%;
  position: absolute;
  z-index: 1;
}
.animationcss {
  animation: window-open 0.5s 1;
}

.animationcss-reverse {
  animation: window-open-reverse 0.5s 1;
}

@keyframes window-open {
  0% {
    opacity: 0;
    -webkit-transform: scale3d(0, 0, 1);
  }

  100% {
    opacity: 1;
    -webkit-transform: scale3d(1, 1, 1);
  }
}

@keyframes window-open-reverse {
  0% {
    opacity: 1;
    -webkit-transform: scale3d(1, 1, 1);
  }

  100% {
    opacity: 0;
    -webkit-transform: scale3d(0, 0, 1);
  }
}

.homeStatement {
  display: flex;
  color: #eaeaea;
  justify-content: space-around;
  align-items: center;
  font-size: 50px;
  width: 100%;
  text-align: center;
}

.homeButton {
  border: 0 none;
  font-size: 20px;
  opacity: 75%;
  width: 120px;
  height: 60px;
  border-radius: 10px;
  margin: 50px 100px 50px 100px;
  cursor: pointer;
}

.homeButton:hover {
  opacity: 100%;
  background-color: lightsteelblue;
}

.video {
  margin: 0px;
  padding: 0px;
  position: fixed;
  right: 0px;
  bottom: 0px;
  min-width: 100%;
  min-height: 100%;
  height: auto;
  width: auto;
  /*加滤镜*/
  /*filter: blur(15px); //背景模糊设置 */
  /*-webkit-filter: grayscale(100%);*/
  /*filter:grayscale(100%); //背景灰度设置*/
  z-index: -11;
}

.footer {
  display: flex;
  flex: 1;
  padding: 2rem 0;
  border-top: 1px solid #eaeaea;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}

.footer a {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
}

.title a {
  color: #0070f3;
  text-decoration: none;
}

.title a:hover,
.title a:focus,
.title a:active {
  text-decoration: underline;
}

.title {
  margin: 0;
  line-height: 1.15;
  font-size: 4rem;
}

.title,
.description {
  text-align: center;
}

.description {
  margin: 4rem 0;
  line-height: 1.5;
  font-size: 1.5rem;
}

.code {
  background: #fafafa;
  border-radius: 5px;
  padding: 0.75rem;
  font-size: 1.1rem;
  font-family: Menlo, Monaco, Lucida Console, Liberation Mono, DejaVu Sans Mono,
    Bitstream Vera Sans Mono, Courier New, monospace;
}

.grid {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  max-width: 800px;
}

.card {
  margin: 1rem;
  padding: 1.5rem;
  text-align: left;
  color: inherit;
  text-decoration: none;
  border: 1px solid #eaeaea;
  border-radius: 10px;
  transition: color 0.15s ease, border-color 0.15s ease;
  max-width: 300px;
}

.card:hover,
.card:focus,
.card:active {
  color: #0070f3;
  border-color: #0070f3;
}

.card h2 {
  margin: 0 0 1rem 0;
  font-size: 1.5rem;
}

.card p {
  margin: 0;
  font-size: 1.25rem;
  line-height: 1.5;
}

.logo {
  width: 100px;
  height: 100px;
  margin: 25px;
}

.mask {
  opacity: 0.6;
}
.activate {
  visibility: visible;
}

@media (max-width: 600px) {
  .grid {
    width: 100%;
    flex-direction: column;
  }
}

@media (prefers-color-scheme: dark) {
  .card,
  .footer {
    border-color: #222;
  }
  .code {
    background: #111;
  }
  .logo img {
    filter: invert(1);
  }
}
</style>
