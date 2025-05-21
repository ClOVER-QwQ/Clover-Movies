<!-- src/views/Cinema.vue -->
<template>
  <div class="cinema-container">
    <div class="filters">
      <div class="filter-group">
        <label>地区：</label>
        <select v-model="selectedDistrict">
          <option v-for="district in districts" :key="district">{{ district }}</option>
        </select>
      </div>
      <div class="filter-group">
        <label>特色：</label>
        <select v-model="selectedFeature">
          <option v-for="feature in features" :key="feature">{{ feature }}</option>
        </select>
      </div>
    </div>

    <div class="cinema-list">
      <div v-for="cinema in filteredCinemas" :key="cinema.id" class="cinema-card">
        <h3>{{ cinema.name }}</h3>
        <p class="address">{{ cinema.address }}</p>
        <p class="distance">{{ cinema.distance }} km</p>
        <div class="tags">
          <span v-for="tag in cinema.tags" :key="tag">{{ tag }}</span>
        </div>
        <button @click="goToBooking(cinema)">选座购票</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';

interface Cinema {
  id: number;
  name: string;
  address: string;
  distance: number;
  tags: string[];
}

const cinemas = ref<Cinema[]>([
  {
    id: 1,
    name: '万达影城（文化旅游城店）',
    address: '包河区南宁路与万年南路交口向南100米',
    distance: 1.8,
    tags: ['IMAX', '杜比全景声']
  },
  // 更多影院数据...
]);

const selectedDistrict = ref('全能');
const selectedFeature = ref('全能');

const districts = ['全能', '包河区', '庐阳区', '蜀山区'];
const features = ['全能', 'IMAX厅', '杜比全景声厅', '儿童厅'];

const filteredCinemas = computed(() => {
  return cinemas.value.filter(cinema => {
    const districtMatch = selectedDistrict.value === '全能' ||
        cinema.address.includes(selectedDistrict.value);
    const featureMatch = selectedFeature.value === '全能' ||
        cinema.tags.includes(selectedFeature.value);
    return districtMatch && featureMatch;
  });
});
</script>

<style scoped>
.cinema-container {
  max-width: 1200px;
  margin: 0 auto;
}

.filters {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
}

.filter-group select {
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.cinema-list {
  display: grid;
  gap: 20px;
}

.cinema-card {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.tags span {
  display: inline-block;
  margin-right: 10px;
  padding: 4px 8px;
  background: #4CAF50;
  color: white;
  border-radius: 4px;
  font-size: 12px;
}
</style>