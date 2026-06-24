<template>
  <div class="login-page">
    <div class="background-glow"></div>

    <div class="login-card">
      <div class="logo-section">
        <div class="bank-logo">🏦</div>
        <h1>Spring Bank</h1>
        <p>Your digital banking experience</p>
      </div>

      <form @submit.prevent="login" class="login-form">
        <div class="input-group">
          <label>User ID</label>
          <input v-model="userId" type="number" placeholder="Enter your user ID" />
        </div>

        <button type="submit" class="login-button">
          Access Dashboard
        </button>
      </form>

      <div class="register-link">
        Don't have an account?

        <router-link to="/register">
          Create one
        </router-link>
      </div>

      <div class="footer-text">
        Secure banking powered by Spring Boot + Vue
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userId: ""
    };
  },

  methods: {
    login() {
      if (!this.userId) {
        alert("Please enter a User ID");
        return;
      }

      this.$router.push(`/dashboard/${this.userId}`);
    }
  }
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

.login-page {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #0f172a, #1e293b, #111827);
  position: relative;
  overflow: hidden;
}

.background-glow {
  position: absolute;
  width: 500px;
  height: 500px;
  background: rgba(59, 130, 246, 0.2);
  border-radius: 50%;
  filter: blur(120px);
}

.login-card {
  width: 420px;
  padding: 40px;
  border-radius: 24px;
  background: rgba(255, 255, 255, 0.08);
  backdrop-filter: blur(18px);
  border: 1px solid rgba(255, 255, 255, 0.12);
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.35);
  z-index: 1;
}

.logo-section {
  text-align: center;
  margin-bottom: 35px;
}

.bank-logo {
  font-size: 56px;
  margin-bottom: 10px;
}

.logo-section h1 {
  color: white;
  font-size: 32px;
  margin-bottom: 8px;
}

.logo-section p {
  color: #cbd5e1;
  font-size: 14px;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 22px;
}

.input-group {
  display: flex;
  flex-direction: column;
}

.input-group label {
  color: #e2e8f0;
  margin-bottom: 8px;
  font-size: 14px;
}

.input-group input {
  padding: 16px;
  border: none;
  border-radius: 14px;
  background: rgba(255, 255, 255, 0.12);
  color: white;
  font-size: 15px;
  outline: none;
  transition: 0.2s ease;
}

.input-group input::placeholder {
  color: #94a3b8;
}

.input-group input:focus {
  background: rgba(255, 255, 255, 0.18);
  border: 1px solid #3b82f6;
}

.login-button {
  margin-top: 10px;
  padding: 16px;
  border: none;
  border-radius: 14px;
  background: linear-gradient(135deg, #2563eb, #3b82f6);
  color: white;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  transition: transform 0.2s ease, opacity 0.2s ease;
}

.login-button:hover {
  transform: translateY(-2px);
  opacity: 0.95;
}

.footer-text {
  margin-top: 28px;
  text-align: center;
  color: #94a3b8;
  font-size: 13px;
}
</style>
