<template>
  <div class="about-view">
    <p>
      RideNShare is a web application for helping Virginia Tech Students and
      staffs to share and get a ride. This app provides a ride sharing service
      for Virginia Tech students and staff exclusively. Both the driver and
      passengers need to verify their vt email before use. In this app, drivers
      can post offer-post to give a ride and passengers can post their
      needs-post.
    </p>
    <div class="place-holder"></div>
    <h3>Our email: ridenshareforvt@gmail.com</h3>
  </div>
</template>

<script>
export default {
  name: "AboutView",
};
</script>

<style scoped>
.about-view {
  margin: 15% 20% 10% 20%;
  text-align: center;
}
.place-holder {
  margin-top: 10%;
}
.about-view p {
  font-size: 1.5rem;
}
</style>
