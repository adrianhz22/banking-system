<template>
  <div class="register-page">

    <div class="register-card">

      <h1>Create Account</h1>
      <p>Open your online banking account</p>

      <input v-model="name" placeholder="Full name" />

      <input v-model="email" type="email" placeholder="Email" />

      <input v-model="password" type="password" placeholder="Password" />

      <button @click="register">
        Create Account
      </button>

      <p class="message">{{ message }}</p>

      <div class="login-link">
        Already have an account?
        <router-link to="/">Sign in</router-link>
      </div>

    </div>

  </div>
</template>

<script>
import api from "../services/api";

export default {

  data() {
    return {
      name: "",
      email: "",
      password: "",
      message: ""
    };
  },

  methods: {

    async register() {

      try {

        await api.post("/api/auth/register", {
          name: this.name,
          email: this.email,
          password: this.password
        });

        this.$router.push("/");

      } catch (err) {

        console.error(err);
        this.message = "Could not create account.";

      }

    }

  }

};
</script>

<style scoped>
.register-page {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #0f172a, #1e3a8a);
  font-family: Inter, sans-serif;
}

.register-card {
  width: 420px;
  background: white;
  border-radius: 22px;
  padding: 40px;
  box-shadow: 0 20px 50px rgba(0, 0, 0, .25);
}

.register-card h1 {
  margin: 0;
  color: #0f172a;
}

.register-card p {
  color: #64748b;
  margin-bottom: 25px;
}

input {
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 18px;
  padding: 15px;
  border-radius: 12px;
  border: 1px solid #dbeafe;
  font-size: 15px;
}

input:focus {
  outline: none;
  border-color: #2563eb;
}

button {

  width: 100%;
  padding: 15px;
  border: none;
  border-radius: 12px;
  background: #2563eb;
  color: white;
  font-size: 16px;
  cursor: pointer;
  transition: .2s;
}

button:hover {

  background: #1d4ed8;

}

.message {

  margin-top: 20px;
  text-align: center;
  color: #ef4444;

}

.login-link {

  margin-top: 25px;
  text-align: center;

}

.login-link a {

  color: #2563eb;
  text-decoration: none;
  font-weight: 600;

}
</style>