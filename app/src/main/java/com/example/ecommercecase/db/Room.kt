package com.example.ecommercecase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.ecommercecase.domain.ProductListItem
import com.example.ecommercecase.network.model.CartListItemModel
import com.example.ecommercecase.utils.toCurrencyFormat

@Dao
interface ProductListDao {
    @Query("select * from ProductListItemDatabase where id = :id")
    fun getProductFromList(id: String): LiveData<ProductListItem>?
    @Query("select * from ProductListItemDatabase")
    fun getProductList(): LiveData<List<ProductListItemDatabase>?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductListItemDatabase>)
}

@Dao
interface CartListItemDao {
    @Query("SELECT * FROM CartListItemDatabase")
    fun getCartList(): LiveData<List<CartListItemDatabase>>

    @Insert
    suspend fun insertCartListItem(cartListItem: CartListItemDatabase)

    @Query("DELETE FROM CartListItemDatabase WHERE id = :id")
    suspend fun deleteCartListItem(id: String)

    @Query("UPDATE CartListItemDatabase SET quantity = :quantity WHERE id = :id")
    suspend fun updateCartListItem(id: String, quantity: Int)

    @Query("DELETE FROM CartListItemDatabase")
    suspend fun deleteAllCartListItems()
}

@Database(entities = [ProductListItemDatabase::class, CartListItemDatabase::class], version = 2, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase(){
    abstract val productListDao: ProductListDao
    abstract val cartListItemDao: CartListItemDao
}


fun List<ProductListItemDatabase>.asProductDomainModel(): List<ProductListItem> {
    return map {
        ProductListItem(
            createdAt = it.createdAt,
            name = it.name,
            image = it.image,
            price = it.price.toCurrencyFormat(),
            description = it.description,
            model = it.model,
            brand = it.brand,
            id = it.id
        )
    }
}

fun List<CartListItemDatabase>.asCartDomainModel(): List<CartListItemModel> {
    return map {
        CartListItemModel(
            id = it.id,
            title = it.title,
            price = it.price,
            image = it.image,
            quantity = it.quantity
        )
    }
}