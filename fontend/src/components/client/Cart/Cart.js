import "./Cart.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { message, Spin } from "antd";
import axios from "axios";
function Cart() {
  const [messageApi, contextHolder] = message.useMessage();
  const navigate = useNavigate();
  const [cartdetails, setCartDetail] = useState([]);
  const userData = JSON.parse(localStorage.getItem("user"));
  const [selectedItems, setSelectedItems] = useState({});

  const selectAllItems = (e) => {
    const isChecked = e.target.checked;
    const updatedSelectedItems = {};
    cartdetails.forEach((item) => {
      updatedSelectedItems[item.id] = isChecked;
    });
    setSelectedItems(updatedSelectedItems);
  };

  const handleSelectItem = (e, itemId) => {
    const isChecked = e.target.checked;
    setSelectedItems({
      ...selectedItems,
      [itemId]: isChecked,
    });
    console.log(selectedItems);
  };

  const errorMessage2 = () => {
    messageApi.open({
      type: "error",
      content: "Vui lòng đăng nhập để thực hiện tính năng này!",
    });
  };

  const handleGetTotal = () => {

    const newOrdersArr = cartdetails.map((order) => ({
      ...order,
      checked: selectedItems[order.id] || false,
    })).filter((order) => order.checked);

    return newOrdersArr.reduce(
      (total, product) => total + (product.price || 0),
      0
    );

  };

  const handleSendCheckout = () => {
    navigate("/checkout", { state: [cartdetails, selectedItems] });
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

          setCartDetail(data);
          console.log(data);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    } else {
      errorMessage2();
    }
  }, []);

  return (
    <div className="Cart">
      <div className="Cart_left">
        <div className="Cart_left_title">
          <h2>Giỏ hàng</h2>
          <span>{cartdetails.length} sản phẩm</span>
        </div>
        <div className="Cart_left_table">
          <table>
            <thead>
              <tr>
                <th>
                  <input type="checkbox" onChange={selectAllItems} />
                </th>
                <th>Sản phẩm</th>
                <th>Số lượng</th>
                <th>Tạm tính</th>
                <th>
                  <i className="fa-solid fa-trash-can"></i>
                </th>
              </tr>
            </thead>
            <tbody>
              {cartdetails.map((cartdetail, index) => {
                return (
                  <CartItem
                    key={index}
                    shoese={cartdetail}
                    handleSelectItem={handleSelectItem}
                    setSelectedItems={setSelectedItems}
                    selectedItems={selectedItems}
                    cartdetails={cartdetails}
                  />
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
      <div className="Cart_right">
        <h3>Hoá đơn tạm tính</h3>
        <div>
          <h4>Tổng sản phẩm ({Object.values(selectedItems).reduce((count, value) => count + (value === true ? 1 : 0), 0)})</h4>
          <span>{handleGetTotal().toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')}đ</span>
        </div>
        <div>
          <h4>Phí vận chuyển</h4>
          <span>50.000đ</span>
        </div>
        <hr></hr>
        <div className="Cart_right_total">
          <h4>Tổng cộng</h4>
          <span>{(handleGetTotal() + 50000).toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')}đ</span>
        </div>

        <button onClick={() => handleSendCheckout()}>
          <span>Tiến hành đặt hàng</span>
          <i className="fa-solid fa-arrow-right"></i>
        </button>
      </div>
    </div>
  );
}

function CartItem(props) {
  const [countProduct, setCountProduct] = useState(1);
  const [odrerDetails, setOrderDetail] = useState([]);
  const [messageApi, contextHolder] = message.useMessage();
  const {
    shoese,
    handleSelectItem,
    setSelectedItems,
    selectedItems,
    cartdetails,
  } = props;
  const userData = JSON.parse(localStorage.getItem("user"));
  const successMessage3 = () => {
    messageApi.open({
      type: "success",
      content: "Xóa sản phẩm thành công",
    });
  };

  const errorMessage3 = () => {
    messageApi.open({
      type: "error",
      content: "Xóa sản phẩm thất bại",
    });
  };
  const deleteCartItem = (iddelete) => {
    console.log(iddelete);
    axios
      .delete(`http://localhost:8080/api/v1/orderItems/delete/${iddelete}`, {
        auth: {
          username: userData.username,
          password: userData.password,
        },
      })
      .then((response) => {
        successMessage3();
        setSelectedItems([]);

        setTimeout(() => {
          window.location.reload();
        }, 500);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        errorMessage3();
      });
  };

  return (
    <tr className="CartItem">
      {contextHolder}
      <td>
        <input
          type="checkbox"
          checked={selectedItems[shoese.id] || false}
          onChange={(e) => handleSelectItem(e, shoese.id)}
        />
      </td>
      <td>
        <div className="CartItem_img">
          <img src={shoese.imgSrc} alt="" />
        </div>
        <div className="CartItem_info">
          <h4>{shoese.name}</h4>
          <div className="CartItem_info_variant">
            <div>
              {shoese.color} - {shoese.size}
            </div>
          </div>
        </div>
      </td>
      <td>
        <div>
          <button
            onClick={() => setCountProduct(Math.max(countProduct - 1, 1))}
          >
            <i className="fa-solid fa-minus"></i>
          </button>
          <span>{countProduct}</span>
          <button onClick={() => setCountProduct(countProduct + 1)}>
            <i className="fa-solid fa-plus"></i>
          </button>
        </div>
      </td>
      <td>{shoese.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")}đ</td>
      <td>
        <div>
          <button className="delete" onClick={() => deleteCartItem(shoese.id)}>
            <i className="fa-solid fa-xmark"></i>
          </button>
        </div>
      </td>
    </tr>
  );
}

export default Cart;
