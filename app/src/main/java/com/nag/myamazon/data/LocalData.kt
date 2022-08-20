package com.nag.myamazon.data

import com.nag.myamazon.model.Mobile

class LocalData {

    companion object {
        fun getData(): MutableList<Mobile> {
            var list = mutableListOf<Mobile>()
            list.add(
                Mobile(
                    101,
                    "F21 Gallxay",
                    "Samsung",
                    9000,
                    "https://m.media-amazon.com/images/I/41ZGJxnJu1S.jpg"
                )
            )
            list.add(
                Mobile(
                    102,
                    "V1 Pro",
                    "Vivo",
                    8000,
                    "https://m.media-amazon.com/images/I/51GkJLzb9NL._SY741_.jpg"
                )
            )
            list.add(
                Mobile(
                    103,
                    "R6 Pro ",
                    "Realme",
                    7000,
                    "https://images-eu.ssl-images-amazon.com/images/I/4147W6koDNL._SX300_SY300_QL70_FMwebp_.jpg"
                )
            )
            list.add(
                Mobile(
                    104,
                    "Oppo Proud",
                    "Oppo",
                    6000,
                    "https://images-eu.ssl-images-amazon.com/images/I/41BnHjRP0ZS._SX300_SY300_QL70_FMwebp_.jpg"
                )
            )
            list.add(
                Mobile(
                    105,
                    "M1 Pro",
                    "Moto",
                    5000,
                    "https://m.media-amazon.com/images/I/31GOIteqGpL._AC_UY218_.jpg"
                )
            )
            list.add(
                Mobile(
                    106,
                    "Note5",
                    "Redmi",
                    4000,
                    "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
                )
            )
            list.add(
                Mobile(
                    107,
                    "In 20",
                    "Infix",
                    3000,
                    "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
                )
            )
            list.add(
                Mobile(
                    108,
                    "N7",
                    "Nokia",
                    2000,
                    "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
                )
            )
            list.add(
                Mobile(
                    109,
                    "G1 Pro",
                    "Ola",
                    1000,
                    "https://m.media-amazon.com/images/I/41P4Al+S3zL._SY300_SX300_.jpg"
                )
            )

            return list;

        }
    }




}