import { Box, Button, Card, CardContent, Typography } from "@mui/material";
import React from "react";
const NotificationsCard = (notificationData) => {
  const { orderStatus, customer, deliveryAddress, items, totalAmount } =
    notificationData.notificationData;
  const cardStyle = {
    borderLeft: `5px solid ${
      orderStatus === "Completed"
        ? "green"
        : orderStatus === "Pending"
        ? "orange"
        : "gray"
    }`,
    margin: "1rem 0",
    padding: "1rem",
    borderRadius: "8px",
    boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
  };
  const handleDelete = (id) => {
    console.log("Order deleted with :- " + id);
  };
  return (
    <Card style={cardStyle}>
      <CardContent>
        <Typography variant="h6" gutterBottom>
          Order for: {customer.fullName}
        </Typography>
        <Typography variant="body1" gutterBottom>
          <strong>Delivery Address:</strong>{" "}
          {`${deliveryAddress.streetAddress}, ${deliveryAddress.city}, ${deliveryAddress.state}`}
        </Typography>
        <Typography variant="body1" gutterBottom>
          <strong>Items:</strong>
        </Typography>
        <ul>
          {items.map((item) => (
            <li key={item.id}>
              {item.food.name} - ₹{item.food.price} x {item.quantity} = ₹
              {item.totalPrice}
            </li>
          ))}
        </ul>
        <Typography variant="body1" gutterBottom>
          <strong>Total Amount:</strong> ₹{totalAmount}
        </Typography>
        <Box
          mt={2}
          display="flex"
          justifyContent="space-between"
          alignItems="center"
          sx={{ position: "relative" }}
        >
          <Typography
            variant="body2"
            style={{
              fontWeight: "bold",
              color: orderStatus === "COMPLETED" ? "green" : "orange",
            }}
          >
            {orderStatus}
          </Typography>
          <Button
            variant="contained"
            color="error"
            onClick={() => handleDelete(notificationData.notificationData.id)}
            size="small"
          >
            Delete
          </Button>
        </Box>
      </CardContent>
    </Card>
  );
};
export default NotificationsCard;
