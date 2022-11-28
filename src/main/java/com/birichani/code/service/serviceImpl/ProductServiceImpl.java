
 /*
  * Author     : birichani.code
  * Date         : 28/11/22  00:29
  * Project Name : childrenRESTfulAPI
  * */


 package com.birichani.code.service.serviceImpl;

 import com.birichani.code.exception.ResourceNotFoundException;
 import com.birichani.code.model.Product;
 import com.birichani.code.payload.ProductDTO;
 import com.birichani.code.payload.ProductResponse;
 import com.birichani.code.repository.ProductRepository;
 import com.birichani.code.service.ProductService;
 import org.modelmapper.ModelMapper;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;
 import org.springframework.data.domain.Sort;


 import java.util.Date;
 import java.util.List;
 import java.util.stream.Collectors;

 public class ProductServiceImpl implements ProductService {
  private ProductRepository productRepository;
  private ModelMapper mapper;

  public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
   this.productRepository = productRepository;
   this.mapper = mapper;
  }



  @Override
  public ProductDTO createProduct(ProductDTO productDTO) {

   // convert DTO to entity
   Product product  = mapToEntity(productDTO);
   Product newProduct=productRepository.save(product);

   // convert entity to DTO
   ProductDTO productResponse=mapToDTO(newProduct);

   return productResponse;
  }

  @Override
  public ProductResponse getAllProduct(int pageNo, int pageSize, String sortBy, String sortDir) {

   Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
           : Sort.by(sortBy).descending();

   // create Pageable instance
   Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

   Page<Product> productPage = productRepository.findAll(pageable);

   // get content for page object
   List<Product> listOfProduct = productPage.getContent();

   List<ProductDTO> content= listOfProduct.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());

   ProductResponse productResponse= new ProductResponse();
   productResponse.setContent(content);
   productResponse.setPageNo(productPage.getNumber());
   productResponse.setPageSize(productPage.getSize());
   productResponse.setTotalElements(productPage.getTotalElements());
   productResponse.setTotalPages(productPage.getTotalPages());
   productResponse.setLast(productPage.isLast());

   return productResponse;
  }

  @Override
  public ProductDTO findByItemsCreatedBetweenLastMonth(Date monthStart, Date endMonth) {
   Product product = (Product) productRepository.findByItemsCreatedBetweenLastMonth(monthStart,endMonth);
   return mapToDTO(product);

  }

  @Override
  public ProductDTO findByItemsReceivedToday(Date dayStart, Date dayEnd) {
   Product product = (Product) productRepository.findByItemsReceivedToday(dayStart,dayEnd);
   return mapToDTO(product);

  }

  @Override
  public ProductDTO findByItemsCreatedBetweenLastWeek(Date weekStart, Date weekEnd) {
   Product product = (Product) productRepository.findByItemsReceivedToday(weekStart,weekEnd);
   return mapToDTO(product);

  }

  @Override
  public ProductDTO getProductById(long id) {
   Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
   return mapToDTO(product);
  }

  @Override
  public ProductDTO updateProduct(ProductDTO productDTO , long id) {
   // get child by id from the database
   Product product  = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

   product.setName(productDTO.getName());
   product.setCreatedTime(productDTO.getCreatedTime());


   Product updatedProduct = productRepository.save(product);
   return mapToDTO(updatedProduct);
  }

  @Override
  public void deleteProductById(long id) {
   // get product by id from the database
   Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product","id",id));
   productRepository.delete(product);
  }

  // convert Entity into DTO
  private ProductDTO mapToDTO(Product product){
   ProductDTO productDTO  = mapper.map(product, ProductDTO.class);
   return productDTO;
  }

  // convert DTO to entity
  private Product mapToEntity(ProductDTO productDTO){
   Product product= mapper.map(productDTO, Product.class);
   return product;
  }
 }
