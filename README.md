# 🍔 Online Food Ordering System

<div align="center">

A **complete, production-ready food ordering platform** with real-time order tracking, payment integration, and responsive design.

[![MERN Stack](https://img.shields.io/badge/MERN-Stack-brightgreen?style=for-the-badge)](https://www.mongodb.com/)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react)](https://reactjs.org/)
[![Node.js](https://img.shields.io/badge/Node.js-18-339933?style=for-the-badge&logo=nodedotjs)](https://nodejs.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-5-47A248?style=for-the-badge&logo=mongodb)](https://www.mongodb.com/)
[![Redux](https://img.shields.io/badge/Redux-purple?style=for-the-badge&logo=redux)](https://redux.js.org/)

</div>

---

## ✨ Features

✅ **User Authentication** - Secure login/signup with JWT tokens  
✅ **Food Catalog** - Browse restaurants and menu items with filters  
✅ **Shopping Cart** - Add/remove items, manage quantities  
✅ **Real-time Order Tracking** - Track order status in real-time  
✅ **Payment Integration** - Secure payment processing with Stripe  
✅ **Order Management** - View order history and details  
✅ **Responsive Design** - Works seamlessly on desktop and mobile  
✅ **Admin Dashboard** - Restaurant management panel  

---

## 🛠️ Tech Stack

### Frontend
- **React 18** - UI library with hooks
- **Redux** - State management
- **Axios** - HTTP client
- **Tailwind CSS / SCSS** - Responsive styling
- **React Router** - Client-side routing

### Backend
- **Node.js** - Runtime environment
- **Express.js** - Web framework
- **MongoDB** - NoSQL database
- **Mongoose** - ODM for MongoDB
- **JWT** - Authentication

### Payment & Integrations
- **Stripe API** - Payment processing
- **Socket.io** - Real-time updates

---

## 🚀 Getting Started

### Prerequisites
- Node.js (v14+)
- MongoDB
- Stripe account

### Installation

```bash
# Clone the repository
git clone https://github.com/Dhiraj-231/Online-Food-ordering.git
cd Online-Food-ordering

# Install dependencies
npm install

# Create .env file
# Add your MongoDB connection string and Stripe keys

# Start the application
npm start
```

---

## 📁 Project Structure

```
Online-Food-ordering/
├── client/                 # React frontend
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── pages/          # Page components
│   │   ├── redux/          # Redux slices
│   │   └── App.js
├── server/                 # Express backend
│   ├── models/             # MongoDB schemas
│   ├── routes/             # API endpoints
│   ├── controllers/        # Request handlers
│   └── server.js
└── README.md
```

---

## 📚 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/restaurants` | Get all restaurants |
| GET | `/api/menu/:restaurantId` | Get restaurant menu |
| POST | `/api/orders` | Create new order |
| GET | `/api/orders/:orderId` | Get order details |
| PATCH | `/api/orders/:orderId` | Update order status |
| POST | `/api/payments` | Process payment |

---

## 🎯 Future Enhancements

- [ ] Push notifications for order updates
- [ ] Rating and review system
- [ ] Coupon/discount codes
- [ ] Multiple payment methods
- [ ] Restaurant analytics dashboard
- [ ] Advanced search and filtering

---

## 👨‍💻 Author

**Dhiraj Ray**  
- GitHub: [@Dhiraj-231](https://github.com/Dhiraj-231)
- Email: scoperaise@gmail.com

---

## 📄 License

This project is open source and available under the MIT License.

---

<div align="center">

**⭐ If you found this helpful, please give it a star!**

Made with ❤️ by Dhiraj Ray

</div>
