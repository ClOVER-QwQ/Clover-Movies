<!-- src/views/Booking.vue -->
<template>
  <div class="booking-container">
    <div class="screen">银幕位置</div>

    <div class="seat-map">
      <div v-for="(row, ri) in seats" :key="ri" class="seat-row">
        <div
            v-for="(seat, si) in row"
            :key="si"
            :class="['seat', seat.status]"
            @click="selectSeat(ri, si)"
        >
          {{ seat.number }}
        </div>
      </div>
    </div>

    <div class="booking-summary">
      <h3>已选座位：{{ selectedSeats.join(', ') }}</h3>
      <p>总价：¥{{ totalPrice }}</p>
      <button @click="confirmBooking">确认支付</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';

interface Seat {
  number: string;
  status: 'available' | 'selected' | 'occupied';
}

const seats = ref<Seat[][]>([
  // 示例座位数据
  [
    { number: 'A1', status: 'available' },
    { number: 'A2', status: 'occupied' },
  ],
  // 更多座位数据...
]);

const selectedSeats = ref<string[]>([]);

const totalPrice = computed(() => selectedSeats.value.length * 45);

const selectSeat = (row: number, col: number) => {
  const seat = seats.value[row][col];
  if (seat.status === 'available') {
    seat.status = 'selected';
    selectedSeats.value.push(seat.number);
  } else if (seat.status === 'selected') {
    seat.status = 'available';
    selectedSeats.value = selectedSeats.value.filter(n => n !== seat.number);
  }
};

const confirmBooking = () => {
  // 实现支付逻辑
};
</script>

<style scoped>
.booking-container {
  max-width: 1000px;
  margin: 0 auto;
}

.screen {
  text-align: center;
  margin: 20px 0;
  font-size: 24px;
  color: #666;
}

.seat-map {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.seat-row {
  display: flex;
  gap: 5px;
  justify-content: center;
}

.seat {
  width: 40px;
  height: 40px;
  border: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
}

.seat.available:hover {
  background: #e8f5e9;
}

.seat.selected {
  background: #4CAF50;
  color: white;
}

.seat.occupied {
  background: #eee;
  cursor: not-allowed;
}

.booking-summary {
  margin-top: 30px;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 8px;
}
</style>