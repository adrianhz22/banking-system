<template>
  <div class="page">

    <div v-if="!user" class="loading">
      Loading user data...
    </div>

    <div v-else class="dashboard">

      <div class="header">
        <div>
          <h1>Welcome, {{ user.name }}</h1>
          <p class="email">{{ user.email }}</p>
        </div>

        <div class="avatar">
          {{ user.name ? user.name.charAt(0) : "U" }}
        </div>
      </div>

       <div class="summary-card">
  <span>Total Balance</span>
  <h2>{{ totalBalance.toFixed(2) }} €</h2>
</div>
      <h2>Your accounts</h2>

      <div class="grid">
        <div class="card" v-for="acc in user.accounts" :key="acc.id">

          <div class="card-top">
            <span>Account</span>
            <small>{{ acc.accountNumber }}</small>
          </div>

          <div class="balance">
            {{ acc.balance.toFixed(2) }} €
          </div>

        </div>
      </div>

    </div>
  </div>
</template>

<script>
import api from "../services/api";

export default {
  data() {
    return {
      user: null
    };
  },

  computed: {
    totalBalance() {
      return (this.user?.accounts || [])
        .reduce((sum, acc) => sum + acc.balance, 0);
    }
  },

  async mounted() {
    try {
      const id = this.$route.params.id;
      const res = await api.get(`/api/users/${id}`);
      this.user = res.data;
    } catch (err) {
      console.error("Error loading user:", err);
    }
  }
};
</script>

<style scoped>
.page {
  min-height: 100vh;
  background: linear-gradient(135deg, #0f172a, #111827);
  color: white;
  font-family: "Inter", sans-serif;
}

.loading {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  color: #cbd5e1;
}

.dashboard {
  max-width: 1200px;
  margin: 0 auto;
  padding: 50px 30px;
}

/* HEADER */

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 50px;
}

.header h1 {
  margin: 0;
  font-size: 38px;
  font-weight: 700;
}

.email {
  color: #94a3b8;
  margin-top: 8px;
  font-size: 15px;
}

.avatar {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  background: linear-gradient(135deg, #2563eb, #3b82f6);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 28px;
  font-weight: bold;
  box-shadow: 0 10px 30px rgba(37, 99, 235, 0.4);
}

h2 {
  margin-bottom: 25px;
  font-size: 22px;
  font-weight: 600;
}

.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
  gap: 25px;
}

.card {
  position: relative;
  overflow: hidden;
  background: rgba(255,255,255,0.06);
  backdrop-filter: blur(12px);
  border: 1px solid rgba(255,255,255,0.08);
  border-radius: 24px;
  padding: 28px;
  transition: all 0.3s ease;
}

.card:hover {
  transform: translateY(-6px);
  border-color: rgba(59,130,246,0.4);
  box-shadow: 0 20px 40px rgba(0,0,0,0.35);
}

.card::before {
  content: "";
  position: absolute;
  top: -40px;
  right: -40px;
  width: 120px;
  height: 120px;
  background: rgba(59,130,246,0.15);
  border-radius: 50%;
}

.card-top {
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #94a3b8;
  margin-bottom: 25px;
  font-size: 13px;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.balance {
  font-size: 36px;
  font-weight: 700;
  color: white;
}

.balance::after {
  content: " EUR";
  font-size: 14px;
  color: #94a3b8;
  margin-left: 6px;
}

.summary-card {
  background: linear-gradient(135deg, #2563eb, #1d4ed8);
  border-radius: 24px;
  padding: 30px;
  margin-bottom: 40px;
  color: white;
  box-shadow: 0 15px 40px rgba(37,99,235,.4);
}

.summary-card span {
  opacity: .8;
  display: block;
  margin-bottom: 10px;
}

.summary-card h2 {
  font-size: 42px;
  margin: 0;
}

@media (max-width: 768px) {
  .header {
    flex-direction: column;
    gap: 20px;
    align-items: flex-start;
  }

  .header h1 {
    font-size: 30px;
  }

  .avatar {
    width: 60px;
    height: 60px;
  }
}
</style>