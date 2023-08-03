package cn.catguild.system.presentation;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.system.domain.Region;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author xiyan
 * @date 2023/5/21 10:44
 */
@RequestMapping("/regions")
@RestController
public class RegionResource {

  @GetMapping("/countries")
  public ApiResponse<List<Region>> countries() {
    List<Region> countries = new ArrayList<>();
    countries.add(Region.builder().code("china").name("中国").sort(1).build());
    return ApiResponse.ok(countries);
  }

  @GetMapping("/countries/{countryCode}/provinces")
  public ApiResponse<List<Region>> provinces(@PathVariable("countryCode") String countryCode) {
    List<Region> provinces = new ArrayList<>();
    provinces.add(Region.builder().code("beijing").name("北京").sort(1).build());
    provinces.add(Region.builder().code("shanghai").name("上海").sort(2).build());
    return ApiResponse.ok(provinces);
  }

  @GetMapping("/countries/{countryCode}/provinces/{provinceCode}/cities")
  public ApiResponse<List<Region>> cities(
      @PathVariable("countryCode") String countryCode,
      @PathVariable("provinceCode") String provinceCode) {
    List<Region> cities = new ArrayList<>();
    cities.add(Region.builder().code("shanghaicity").name("上海市").sort(1).build());
    return ApiResponse.ok(cities);
  }

}
