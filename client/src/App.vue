<template>
  <div id="app">
    <app-header v-if="currPath !== '/'"></app-header>
    <router-view></router-view>
    <app-footer v-if="currPath !== '/'"></app-footer>
  </div>
</template>

<style></style>

<script>
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/components/AppFooter.vue";

export default {
  components: {
    AppHeader,
    AppFooter,
  },
  data() {
    return {
      currPath: "/",
    };
  },
  mounted() {
    this.currPath = this.$route.path;
  },
  watch: {
    $route(to, from) {
      this.currPath = this.$route.path;
      if (this.currPath === "/" && to.path === "/explore") {
        location.reload();
      }
    },
  },
};
</script>
