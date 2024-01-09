import "./Order.css"

function Order() {
    return(
        <>
        <div className="all">
            <div className="orderstatus">             
                    <a onclick="">Chờ xác nhận</a>
                    <a onclick="">Chờ giao hàng</a>
                    <a onclick="">Đang giao hàng</a>
                    <a onclick="">Đã giao</a>
                    <a onclick="">Đã hủy</a>       
            </div>
            <div className="orderItem">
                <div>
                    <img></img>
                    <p>tem</p>
                    
                </div>
            </div>
        </div>
        
        </>
    )
}
export default Order;