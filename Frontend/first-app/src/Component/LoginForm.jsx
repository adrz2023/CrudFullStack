import React, { useState } from "react";
import axios from "axios";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  function verify(e) {
    e.preventDefault();
    axios
      .post(`http://localhost:8080/api/login?email=${email}&password=${password}`)
      .then((res) => {
        alert("Login successfully");
        localStorage.setItem("Admin", JSON.stringify(res.data));
        setError(null);
      })
      .catch((err) => {
        setError(err.response?.data || "Invalid login credentials");
      });
  }

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={verify}>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <div style={{ color: "red" }}>{error}</div>}
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginForm;
