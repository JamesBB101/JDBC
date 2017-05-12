package idv.david.dao;

import java.util.List;

public interface HouseDAO {
	// 新增一堆房屋資料
	void addAll(List<House> houseList);
	// 新增一筆房屋資料
	void add(House house);
	// 查詢全部房屋資料
	List<House> getAll();
	// 用行政區查資料
	List<House> getByLocation(String location);
}
