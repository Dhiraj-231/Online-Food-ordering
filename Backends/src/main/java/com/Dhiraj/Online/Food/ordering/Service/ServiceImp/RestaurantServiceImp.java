package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Dhiraj.Online.Food.ordering.Dto.RestaurantDto;
import com.Dhiraj.Online.Food.ordering.Exceptions.RestaurantException;
import com.Dhiraj.Online.Food.ordering.Model.Address;
import com.Dhiraj.Online.Food.ordering.Model.Restaurant;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Repository.AddressRepository;
import com.Dhiraj.Online.Food.ordering.Repository.RestaurantRepository;
import com.Dhiraj.Online.Food.ordering.Repository.UserRepository;
import com.Dhiraj.Online.Food.ordering.Request.CreateRestaurantRequest;
import com.Dhiraj.Online.Food.ordering.Service.RestaurantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImp implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final UserServiceImp userService;
    private final UserRepository userRepository;

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setTitle(restaurant.getName());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurant.getId());
        dto.setDescription(restaurant.getDescription());

        boolean isFavorited = false;
        List<RestaurantDto> favorites = user.getFavorites();
        for (RestaurantDto favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }

        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(dto);
        }

        User updatedUser = userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = new Address();
        address.setCity(req.getAddress().getCity());
        address.setCountry(req.getAddress().getCountry());
        address.setFullName(req.getAddress().getFullName());
        address.setPostalCode(req.getAddress().getPostalCode());
        address.setState(req.getAddress().getState());
        address.setStreetAddress(req.getAddress().getStreetAddress());

        Address savedAddress = addressRepository.save(address);
        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(savedAddress);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(req.getRegistrationDate());
        restaurant.setOwner(user);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant != null) {
            restaurantRepository.delete(restaurant);
            return;
        }
        throw new RestaurantException("Restaurant with id " + restaurantId + " Not found");
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
        }
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
        Restaurant restaurants = restaurantRepository.findByOwnerId(userId);
        return restaurants;
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
            throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }

}
