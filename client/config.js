class Fetch {
  constructor() {
    this.base_url = 'http://26.69.27.44:8080/wg.server';
  }

  async post(url = '', data = {}) {
    const response = await fetch(`${this.base_url}/${url}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data)
    });
    return response.json();
  }
  async postJWT(url = '', data = {}) {
    const token = localStorage.getItem("token");
    const response = await fetch(`${this.base_url}/${url}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify(data)
    });
    return response.json();
  }

  async get(url = '') {
    const response = await fetch(`${this.base_url}/${url}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return response.json();
  }

  async getJWT(url = '') {
    const token = localStorage.getItem("token");
    const response = await fetch(`${this.base_url}/${url}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
    });
    return response.json();
  }
}