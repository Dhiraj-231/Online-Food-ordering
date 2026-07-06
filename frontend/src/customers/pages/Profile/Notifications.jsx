import { Button } from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUsersNotificationAction } from "../../../State/Customers/Orders/Action";
import NotificationsCard from "../../components/Notification/NotificationCard";

const Notifications = () => {
  const token = localStorage.getItem("jwt");
  const dispatch = useDispatch();

  const { order } = useSelector((store) => store);
  const { orders } = order;
  const handleClearNotifications = () => {};
  useEffect(() => {
    dispatch(getUsersNotificationAction(token));
  }, []);

  return (
    <div className="space-y-5 px-5 lg:px-20">
      <h1 className="py-5 font-bold text-2xl text-center">Notifications</h1>
      <Button
        variant="contained"
        color="secondary"
        onClick={handleClearNotifications}
      >
        Clear All Notifications
      </Button>
      {orders.map((order) => (
        <NotificationsCard key={order.id} notificationData={order} />
      ))}
    </div>
  );
};

export default Notifications;
