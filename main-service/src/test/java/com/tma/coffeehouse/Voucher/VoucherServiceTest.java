package com.tma.coffeehouse.Voucher;

import com.tma.coffeehouse.Cart.Cart;
import com.tma.coffeehouse.Cart.CartRepository;
import com.tma.coffeehouse.Cart.CartService;
import com.tma.coffeehouse.Cart.DTO.GetFullCartDTO;
import com.tma.coffeehouse.CartDetails.CartDetail;
import com.tma.coffeehouse.CartDetails.CartDetailRepository;
import com.tma.coffeehouse.CartDetails.DTO.DetailOfCartDTO;
import com.tma.coffeehouse.Customers.Customer;
import com.tma.coffeehouse.Customers.CustomerRepository;
import com.tma.coffeehouse.Product.Product;
import com.tma.coffeehouse.Product.ProductRepository;
import com.tma.coffeehouse.User.User;
import com.tma.coffeehouse.Utils.CustomUtils;
import com.tma.coffeehouse.Voucher.DTO.AlmostValidDTO;
import com.tma.coffeehouse.Voucher.DTO.VoucherDTO;

import com.tma.coffeehouse.Voucher.Mapper.AddVoucherMapper;
import com.tma.coffeehouse.Voucher.Mapper.VoucherMapper;
import com.tma.coffeehouse.Voucher.Mapper.VoucherMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class VoucherServiceTest {
    @Autowired
    private VoucherMapper voucherMapper;
    @Mock
    private VoucherRepository voucherRepository;
    @Mock
    private CartService cartService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartDetailRepository cartDetailRepository;
    @InjectMocks
    private VoucherServiceImpl underTest;
    @Mock
    private AddVoucherMapper addVoucherMapper;
    private Product product1En;
    private Product product2En;
    private Product product3En;
    private Product product4En;
    private Customer customerEn;

    @BeforeEach
    void init(){
        this.voucherMapper = new VoucherMapperImpl();
        this.underTest.setVoucherMapper(this.voucherMapper);
        Product.ProductBuilder productBuilder = Product.builder();
         product1En = productBuilder.productCategory(null)
                .id(1L).name("Cà phê").productToppings(new HashSet<>()).image("").description("").price(20000).build();
         product2En = productBuilder.productCategory(null)
                .id(2L).name("Trà ô long").productToppings(new HashSet<>()).image("").description("").price(25000).build();
         product3En = productBuilder.productCategory(null)
                .id(3L).name("Sữa chua").productToppings(new HashSet<>()).image("").description("").price(30000).build();
         product4En = productBuilder.productCategory(null)
                .id(4L).name("Cà phê sữa").productToppings(new HashSet<>()).image("").description("").price(15000).build();
         customerEn = new Customer(1L, "", "", new User(), null, null);
    }

    @Test
    void itShouldFindAllVoucherAvailableForACart_WhenCartDetailContainAllProductsOfAVoucher() {
        Product.ProductBuilder productBuilder = Product.builder();
        // GIVEN
        //  Create Vouchers
        Voucher.VoucherBuilder voucherBuilder = Voucher.builder();
        Set<Product> voucher1Products = new HashSet<>();
        voucher1Products.add(product1En);
        voucher1Products.add(product2En);
        Voucher voucher1En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(10).percentage(0.4F).products(voucher1Products).id(1L)
                .build();
        Set<Product> voucher2Products = new HashSet<>();
        voucher2Products.add(product3En);
        Voucher voucher2En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(10).percentage(0.4F).products(voucher2Products).id(2L)
                .build();

        Set<Voucher> allAvailables = new HashSet<>();
        allAvailables.add(voucher1En);
        allAvailables.add(voucher2En);
        

        //  Create Cart Detail
        DetailOfCartDTO detailOfCartDTO1 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product1En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        DetailOfCartDTO detailOfCartDTO2 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product2En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        Set<DetailOfCartDTO> detailOfCartDTOS = new HashSet<>();
        detailOfCartDTOS.add(detailOfCartDTO1);
        detailOfCartDTOS.add(detailOfCartDTO2);
        GetFullCartDTO getFullCartDTO = GetFullCartDTO.builder()
                .note("")
                .id(2L)
                .details(detailOfCartDTOS)
                .note("")
                .tongtien(45000L)
                .tongtien(45000L)
                .createdAt(new Timestamp(new Date().getTime()))
                .updatedAt(new Timestamp(new Date().getTime()))
                .customer(customerEn)
                .build();

        // WHEN
        when(voucherRepository.findAllAvailable(any(Date.class))).thenReturn(allAvailables);
        when(cartService.findOne(customerEn.getId())).thenReturn(getFullCartDTO);
        Set<VoucherDTO> valids = underTest.findAllVoucherForCart(customerEn.getId()).getValid();


        // THEN
        assertThat(valids).contains(voucherMapper.modelTODto(voucher1En));
        assertThat(valids).doesNotContain(voucherMapper.modelTODto(voucher2En));
    }
    @Test
    void itShouldFindAllVoucherAvailableForACart_WhenCartDetailNotContainAllProductsOfAVoucher() {
        Product.ProductBuilder productBuilder = Product.builder();
        // GIVEN
        //  Creating product
        

        //  Create Vouchers
        Voucher.VoucherBuilder voucherBuilder = Voucher.builder();
        Set<Product> voucher1Products = new HashSet<>();
        voucher1Products.add(product1En);
        voucher1Products.add(product2En);
        Voucher voucher1En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(10).percentage(0.4F).products(voucher1Products).id(1L)
                .build();
        Set<Product> voucher2Products = new HashSet<>();
        voucher2Products.add(product3En);
        Voucher voucher2En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(10).percentage(0.4F).products(voucher2Products).id(2L)
                .build();

        Set<Voucher> allAvailables = new HashSet<>();
        allAvailables.add(voucher1En);
        allAvailables.add(voucher2En);

        //  Create Customer
        

        //  Create Cart Detail
        DetailOfCartDTO detailOfCartDTO1 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product1En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        Set<DetailOfCartDTO> detailOfCartDTOS = new HashSet<>();
        detailOfCartDTOS.add(detailOfCartDTO1);
        GetFullCartDTO getFullCartDTO = GetFullCartDTO.builder()
                .note("")
                .tongtien(45000L)
                .id(2L)
                .details(detailOfCartDTOS)
                .note("")
                .createdAt(new Timestamp(new Date().getTime()))
                .updatedAt(new Timestamp(new Date().getTime()))
                .customer(customerEn)
                .build();

        // WHEN
        when(voucherRepository.findAllAvailable(any(Date.class))).thenReturn(allAvailables);
        when(cartService.findOne(customerEn.getId())).thenReturn(getFullCartDTO);
        Set<VoucherDTO> valids = underTest.findAllVoucherForCart(customerEn.getId()).getValid();
        System.out.println(valids);
        System.out.println(valids.size());


        // THEN
        assertThat(valids).doesNotContain(voucherMapper.modelTODto(voucher1En));
        assertThat(valids).doesNotContain(voucherMapper.modelTODto(voucher2En));
    }
    @Test
    void itShouldFindAllVoucherAvailableForACart_WhenManyValidVoucherForTheSameProducts() {
        Product.ProductBuilder productBuilder = Product.builder();
        // GIVEN
        //  Creating product
        

        //  Create Vouchers
        Voucher.VoucherBuilder voucherBuilder = Voucher.builder();
        Set<Product> voucher1Products = new HashSet<>();
        voucher1Products.add(product1En);
        voucher1Products.add(product2En);
        Voucher voucher1En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(5).percentage(0.4F).products(voucher1Products)
                .id(1L)
                .build();
        Set<Product> voucher2Products = new HashSet<>();
        voucher2Products.add(product2En);
        Voucher voucher2En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(10).percentage(0.4F).products(voucher2Products)
                .id(2L)
                .build();

        Set<Voucher> allAvailables = new HashSet<>();
        allAvailables.add(voucher1En);
        allAvailables.add(voucher2En);

        //  Create Customer
        

        //  Create Cart Detail
        DetailOfCartDTO detailOfCartDTO1 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product1En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        DetailOfCartDTO detailOfCartDTO2 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product2En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        Set<DetailOfCartDTO> detailOfCartDTOS = new HashSet<>();
        detailOfCartDTOS.add(detailOfCartDTO1);
        detailOfCartDTOS.add(detailOfCartDTO2);
        GetFullCartDTO getFullCartDTO = GetFullCartDTO.builder()
                .note("")
                .id(2L)
                .details(detailOfCartDTOS)
                .note("")
                .createdAt(new Timestamp(new Date().getTime()))
                .updatedAt(new Timestamp(new Date().getTime()))
                .customer(customerEn)
                .tongtien(45000L)
                .build();

        // WHEN
        when(voucherRepository.findAllAvailable(any(Date.class))).thenReturn(allAvailables);
        when(cartService.findOne(customerEn.getId())).thenReturn(getFullCartDTO);
        Set<VoucherDTO> valids = underTest.findAllVoucherForCart(customerEn.getId()).getValid();
        System.out.println(valids);
        System.out.println(valids.size());


        // THEN
        assertThat(valids).contains(voucherMapper.modelTODto(voucher1En));
        assertThat(valids).contains(voucherMapper.modelTODto(voucher2En));
    }

    @Test
    void itShouldFindAllProductsMissingToApplyAVoucher(){
        Product.ProductBuilder productBuilder = Product.builder();
        // GIVEN
        //  Creating product
        

        //  Create Vouchers
        Voucher.VoucherBuilder voucherBuilder = Voucher.builder();
        Set<Product> voucher1Products = new HashSet<>();
        voucher1Products.add(product1En);
        voucher1Products.add(product2En);
        voucher1Products.add(product3En);
        Voucher voucher1En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(5).percentage(0.4F).products(voucher1Products)
                .id(1L)
                .build();
        Set<Product> voucher2Products = new HashSet<>();
        voucher2Products.add(product2En);
        Voucher voucher2En = voucherBuilder.startDate(CustomUtils.convertStringToDate("10-02-2023"))
                .endDate(CustomUtils.convertStringToDate("10-03-2023"))
                .maxDiscount(50000L).minOrderTotal(20000L).limitNumber(10).percentage(0.4F).products(voucher2Products)
                .id(2L)
                .build();

        Set<Voucher> allAvailables = new HashSet<>();
        allAvailables.add(voucher1En);
        allAvailables.add(voucher2En);


        //  Create Customer
        

        //  Create Cart Detail
        DetailOfCartDTO detailOfCartDTO1 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product1En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        DetailOfCartDTO detailOfCartDTO2 = DetailOfCartDTO.builder()
                .id(1L)
                .product(product2En)
                .toppings(new HashSet<>())
                .unit(null)
                .build();
        Set<DetailOfCartDTO> detailOfCartDTOS = new HashSet<>();
        detailOfCartDTOS.add(detailOfCartDTO1);
        detailOfCartDTOS.add(detailOfCartDTO2);
        GetFullCartDTO getFullCartDTO = GetFullCartDTO.builder()
                .note("")
                .id(2L)
                .details(detailOfCartDTOS)
                .note("")
                .createdAt(new Timestamp(new Date().getTime()))
                .updatedAt(new Timestamp(new Date().getTime()))
                .customer(customerEn)
                .tongtien(45000L)
                .build();

        // WHEN
        when(voucherRepository.findAllAvailable(any(Date.class))).thenReturn(allAvailables);
        when(cartService.findOne(customerEn.getId())).thenReturn(getFullCartDTO);
        Set<VoucherDTO> valids = underTest.findAllVoucherForCart(customerEn.getId()).getValid();
        Set<AlmostValidDTO> almostValids = underTest.findAllVoucherForCart(customerEn.getId()).getAlmostValid();
        System.out.println(valids);
        System.out.println(valids.size());


        // THEN
        assertThat(valids).contains(voucherMapper.modelTODto(voucher2En));
        assertThat(almostValids).hasSize(1);
        boolean hasMissingProduct = false;
        AlmostValidDTO almostValidVoucher = almostValids.iterator().next();
        if (almostValidVoucher.getVoucher().equals(voucher1En) && almostValidVoucher.getProducts().contains(product3En)){
            hasMissingProduct = true;
        }
        assertThat(hasMissingProduct);


    }

}