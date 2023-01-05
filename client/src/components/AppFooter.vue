<script setup lang="ts"></script>

<style scoped>
footer {
  position: fixed;
  bottom: 0;
  background: #cf4420;
  color: white;
  display: flex;
  width: 100%;
  gap: 1em;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-items: center;
  padding: 5px 0;
}

a {
  color: white;
  text-decoration: none;
}

a:hover {
  color: #ffd04b;
}

.links,
.social-media-icons {
  gap: 0.5em;
}

img {
  color: white;
  height: 40px;
}
</style>

<template>
  <footer>
    <div id="author">2022 RideNShare</div>
    <div class="links center">
      <router-link to="/about">About</router-link>
      <router-link to="/about">Contact</router-link>
      <router-link to="/about">Directions</router-link>
    </div>
    <div class="social-media-icons center">
      <router-link to="/">
        <img src="@/assets/images/icon/instagram.png" alt="instagram" />
      </router-link>
      <router-link to="/">
        <img src="@/assets/images/icon/facebook.png" alt="facebook" />
      </router-link>
      <router-link to="/">
        <img src="@/assets/images/icon/twitter.png" alt="twitter" />
      </router-link>
    </div>
  </footer>
</template>
