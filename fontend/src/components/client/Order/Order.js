import "./Order.css"
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { message, Spin } from "antd";
import axios from "axios";
function Order() {
    const [messageApi, contextHolder] = message.useMessage();
    const userData = JSON.parse(localStorage.getItem("user"));
    const [orderList, setOrderList] = useState([]);
    const errorMessage = () => {
        messageApi.open({
                type: "error",
                content: "Vui lòng đăng nhập để thực hiện tính năng này!",
            });
    };
    useEffect(() => {
        window.scrollTo(0, 0);
        if (userData?.role === "CUSTOMER") {
          const id = userData.id;
          axios
            .get(`http://localhost:8080/api/v1/orders/getCartByCustomer/${id}`, {
              auth: {
                username: userData.username,
                password: userData.password,
              },
            })
            .then((response) => {
              const data = response.data.map((pd, index) => {
                return {
                  imgSrc: pd.imgSrc,
                  name: pd.name,
                  brand: pd.brand,
                  price: pd.price,
                  size: pd.size,
                  color: pd.color,
                  id: pd.orderItem_id,
                };
              });
    
              setOrderList(data);
              console.log(data);
            })
            .catch((error) => {
              console.error("Error fetching data:", error);
            });
        } else {
          errorMessage();
        }
      }, []);
    return(
        
        <div className="all">
            <div className="orderstatus">             
                    <a onclick="">Chờ xác nhận</a>
                    <a onclick="">Chờ giao hàng</a>
                    <a onclick="">Đang giao hàng</a>
                    <a onclick="">Đã giao</a>
                    <a onclick="">Đã hủy</a> 
                    <a onclick="">Đánh giá</a>      
            </div>
            <div className="orderItem">
                <div>
                    <img></img>
                    <p>tem</p>
                    
                </div>
            </div>
        </div>
        
    
    )
}
export default Order;