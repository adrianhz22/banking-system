<template>
  <div class="transfer-page">

    <div v-if="!user" class="loading">
      Loading...
    </div>

    <div v-else class="transfer-container">

      <div class="transfer-card">

        <div class="header">
          <h1>New Transfer</h1>
          <p>Send money securely between accounts</p>
        </div>

        <div class="account-preview">
          <span>Sending from</span>
          <h3>
            {{
              user.accounts.find(a => a.id === fromAccountId)?.accountNumber
            }}
          </h3>
          <p>
            Available balance:
            <strong>
              {{
                user.accounts.find(a => a.id === fromAccountId)?.balance.toFixed(2)
              }} €
            </strong>
          </p>
        </div>

        <div class="form-group">
          <label>Origin account</label>

          <select v-model="fromAccountId">
            <option
              v-for="acc in user.accounts"
              :key="acc.id"
              :value="acc.id"
            >
              {{ acc.accountNumber }} • {{ acc.balance.toFixed(2) }} €
            </option>
          </select>
        </div>

        <div class="form-group">
          <label>Destination account ID</label>

          <input
            v-model="toAccountId"
            type="number"
            placeholder="Enter destination account"
          />
        </div>

        <div class="form-group">
          <label>Amount (€)</label>

          <input
            v-model="amount"
            type="number"
            step="0.01"
            placeholder="0.00"
          />
        </div>

        <button class="send-btn" @click="sendTransfer">
          Send Money
        </button>

        <div
          v-if="message"
          class="message"
          :class="{ success: message.includes('successful') }"
        >
          {{ message }}
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
      user: null,
      fromAccountId: null,
      toAccountId: null,
      amount: 0,
      message: ""
    };
  },

  async mounted() {
    const userId = this.$route.params.userId;

    const res = await api.get(`/api/users/${userId}`);
    this.user = res.data;

    if (this.user.accounts.length > 0) {
      this.fromAccountId = this.user.accounts[0].id;
    }
  },

  methods: {
    async sendTransfer() {
      try {
        await api.post("/api/transfers", {
          fromAccountId: this.fromAccountId,
          toAccountId: this.toAccountId,
          amount: this.amount
        });

        this.message = "Transfer successful ✔️";

      } catch (err) {
        console.error(err);
        this.message = "Transfer failed ❌";
      }
    }
  }
};
</script>

<style scoped>
.transfer-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #0f172a, #1e293b);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px;
  font-family: Inter, Arial, sans-serif;
}

.loading {
  color: white;
  font-size: 18px;
}

.transfer-container {
  width: 100%;
  max-width: 650px;
}

.transfer-card {
  background: white;
  border-radius: 24px;
  padding: 35px;
  box-shadow: 0 20px 50px rgba(0,0,0,0.2);
}

.header {
  text-align: center;
  margin-bottom: 30px;
}

.header h1 {
  margin: 0;
  font-size: 32px;
  color: #0f172a;
}

.header p {
  margin-top: 8px;
  color: #64748b;
}

.account-preview {
  background: linear-gradient(135deg, #2563eb, #1d4ed8);
  color: white;
  padding: 20px;
  border-radius: 18px;
  margin-bottom: 25px;
}

.account-preview span {
  font-size: 13px;
  opacity: 0.8;
}

.account-preview h3 {
  margin: 8px 0;
  font-size: 22px;
}

.account-preview p {
  margin: 0;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
  color: #334155;
}

input,
select {
  width: 100%;
  padding: 14px;
  border-radius: 12px;
  border: 1px solid #dbeafe;
  font-size: 15px;
  transition: all .2s;
  box-sizing: border-box;
}

input:focus,
select:focus {
  outline: none;
  border-color: #2563eb;
  box-shadow: 0 0 0 4px rgba(37,99,235,.15);
}

.send-btn {
  width: 100%;
  border: none;
  border-radius: 14px;
  padding: 16px;
  font-size: 16px;
  font-weight: 600;
  background: #2563eb;
  color: white;
  cursor: pointer;
  transition: .2s;
}

.send-btn:hover {
  background: #1d4ed8;
  transform: translateY(-2px);
}

.message {
  margin-top: 20px;
  text-align: center;
  padding: 12px;
  border-radius: 12px;
  background: #fee2e2;
  color: #991b1b;
}

.message.success {
  background: #dcfce7;
  color: #166534;
}
</style>