<template>
  <div class="center signup-box" id="register" v-title data-title="Register">
    <register-floater></register-floater>
  </div>
</template>

<style scoped>
#login {
  min-width: 100%;
  min-height: 100%;
}

.me-video-player {
  background-color: transparent;
  width: 100%;
  height: 100%;
  object-fit: fill;
  display: block;
  position: absolute;
  left: 0;
  z-index: 0;
  top: 0;
}
.signup-box {
  /*margin-top: 200px;*/
}
.me-login-box {
  width: 300px;
  height: 320px;
  background-color: white;
  margin-top: 150px;
  margin-bottom: 150px;
  left: 50%;
  padding: 30px;
}

.me-login-box-radius {
  border-radius: 10px;
  box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
}

.me-login-box h1 {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  vertical-align: middle;
}

.me-login-design {
  text-align: center;
  font-family: "Open Sans", sans-serif;
  font-size: 18px;
}

.me-login-design-color {
  color: #5fb878 !important;
}

.me-login-button {
  text-align: center;
}

.me-login-button button {
  width: 100%;
}
</style>

<script>
import RegisterFloater from "@/components/RegisterFloater";

export default {
  name: "RegisterView.vue",
  components: {
    RegisterFloater,
  },
};
</script>
