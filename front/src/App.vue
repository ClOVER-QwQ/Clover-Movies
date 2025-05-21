<!-- src/App.vue -->
<template>
  <div id="app" class="background-container">
    <Header />
    <main class="main-content">
      <router-view />
    </main>
    <Footer />
  </div>
</template>

<script setup lang="ts">
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
</script>

<style scoped>
.background-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-image: url('@/assets/mainbk.jpg');
  background-size: cover;
  background-attachment: fixed;
}

.main-content {
  flex: 1;
  padding: 20px;
  margin-top: 160px; /* 根据Header高度调整 */
  margin-bottom: 60px; /* Footer高度 */
}
</style>