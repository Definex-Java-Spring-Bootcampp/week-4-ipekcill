package com.patika.kredinbizdeservice.mapper;

import com.patika.kredinbizdeservice.controller.model.*;
import com.patika.kredinbizdeservice.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ModelMapper {
    public static final ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    public abstract CreditCard toCreditCard(CreditCardDto creditCardDto);

    public abstract CreditCardDto toCreditCardDto(CreditCard creditCard);

    public abstract List<CreditCardDto> toCreditCardDtoList(List<CreditCard> creditCards);

    public abstract Bank toBank(BankDto bankDto);

    public abstract BankDto toBankDto(Bank bank);

    public abstract List<BankDto> toBankDtoList(List<Bank> banks);

    public abstract Campaign toCampaign(CampaignDto campaignDto);

    public abstract CampaignDto toCampaignDto(Campaign campaign);

    public abstract List<CampaignDto> toCampaigntoList(List<Campaign> campaigns);

    @Mapping(source = "password", target = "password", qualifiedByName = "hashPassword")
    public abstract User toUser(UserDto userDto);

    public abstract UserDto toUserDto(User user);

    public abstract List<UserDto> toUserDtoList(List<User> users);
    public abstract Address toAddress(AddressDto addressDto);
    public abstract AddressDto toAddressDto(Address address);

    @Named("hashPassword")
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
