
import { Link } from "react-router-dom";
import "./Sale.css"
import { useEffect, useState } from "react";
import axios from 'axios';

function Sale() {

    const [products, setProducts] = useState([]);
    const [productsFiltered, setProductsFiltered] = useState([]);
    const [typeFilter, setTypeFilter] = useState([]);
    const [colorFilter, setColorFilter] = useState("all");
    const [sizeFilter, setSizeFilter] = useState("all");

    const fetchData = () => {
        axios.get('http://localhost:8080/api/v1/productTypes/full')
            .then(response => {

                let formattedType = response.data.map((_type) => {
                    return ({
                        ..._type,
                        selected: false
                    })
                })

                formattedType.unshift({
                    id: -1,
                    name: "Tất cả",
                    selected: true
                });

                setTypeFilter(formattedType);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }

    const handleChangeTypeFilter = (_id) => {
        const newTypeFilter = typeFilter.map(_type => {
            if (_type.id === _id) {
                return ({
                    ..._type,
                    selected: true
                })
            } else {
                return ({
                    ..._type,
                    selected: false
                })
            };
        })

        setTypeFilter(newTypeFilter);
    }

    useEffect(() => {

        const selectedType = typeFilter.filter((_type) => { return _type.selected === true })[0];

        console.log(selectedType);

        let newProducts = products;

        if (selectedType?.name !== "Tất cả") {
            newProducts = newProducts.filter((product) => {
                return (
                    product.type_name === selectedType.name
                )
            })
        }

        setProductsFiltered(newProducts);

    }, [typeFilter])

    useEffect(() => {
        window.scrollTo(0, 0);

        fetchData();

        axios.get('http://localhost:8080/api/v1/products/full')
            .then(response => {
                console.log(response.data);
                setProducts(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, [])

    return (
        <div className="Shop">
            <div className="Shop_left">
                <div className="filter_type">
                    <span>LOẠI</span>
                    <div>
                        {
                            typeFilter?.map((_type) => {
                                return (
                                    <button className={_type.selected ? "active_filter" : ""} key={_type.id} onClick={() => handleChangeTypeFilter(_type.id)}>{_type.name}</button>
                                )
                            })
                        }
                    </div>
                </div>
                <div className="filter_color">
                    <span>MÀU SẮC</span>
                    <div>
                        <button className="active_filter">All</button>
                        <button style={{ backgroundColor: "#23A9ED" }}></button>
                        <button style={{ backgroundColor: "#1E68B2" }}></button>
                        <button style={{ backgroundColor: "#F0E68C" }}></button>
                        <button style={{ backgroundColor: "#F9F9F0" }}></button>
                        <button style={{ backgroundColor: "#E07233" }}></button>
                        <button style={{ backgroundColor: "#DD3333" }}></button>
                        <button style={{ backgroundColor: "#91541E" }}></button>
                        <button style={{ backgroundColor: "#FFFFFF" }}></button>
                        <button style={{ backgroundColor: "#BA9E1A" }}></button>
                        <button style={{ backgroundColor: "#878787" }}></button>
                        <button style={{ backgroundColor: "#57A533" }}></button>
                    </div>
                </div>
                <div className="filter_size">
                    <span>SIZE</span>
                    <div>
                        <button className="active_filter">All</button>
                        <button>35</button>
                        <button>36</button>
                        <button>37</button>
                        <button>38</button>
                        <button>39</button>
                        <button>40</button>
                        <button>41</button>
                        <button>42</button>
                        <button>43</button>
                    </div>
                </div>
            </div>

            <div className="Shop_right">
                {
                    productsFiltered.map((shoes, index) => {
                        return (
                            <Link className="Shop_right_shoes" key={index} to={`/product/${shoes.id}`}>
                                <div className="shoes_top">
                                    <div className="shoes_top_img">
                                        {shoes.sale_percent !== 0 && <span className="shoes_tags">-{shoes.sale_percent}%</span>}
                                        <img src={shoes.image_url} alt="" lazy="true" />
                                    </div>
                                    <div className="shoes_top_info">
                                        <h3>{shoes.name}</h3>
                                        <span>{shoes.type_name.toUpperCase()}</span>
                                    </div>

                                </div>
                                <div className="shoes_bottom">
                                    <h4>{shoes.price} Vnđ</h4>
                                    {/* <span> {(shoes.price * (1 - shoes.sale_percent / 100)).toFixed(2)}Vnđ</span> */}
                                </div>
                            </Link>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default Sale;