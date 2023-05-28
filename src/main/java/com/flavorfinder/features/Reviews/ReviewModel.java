package com.flavorfinder.features.Reviews;

import com.flavorfinder.shared.utils.DBHandler;
import org.sqlite.core.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewModel {
    private int review_id;
    private String review_type;
    private String review_text;
    private int review_rating;
    private int user_id;
    private int community_recipe_id;
    private int user_recipe_id;

    public ReviewModel(int review_id, String review_type, String review_text, int review_rating, int user_id, int community_recipe_id, int user_recipe_id) {
        this.review_id = review_id;
        this.review_type = review_type;
        this.review_text = review_text;
        this.review_rating = review_rating;
        this.user_id = user_id;
        this.community_recipe_id = community_recipe_id;
        this.user_recipe_id = user_recipe_id;
    }

    public ReviewModel(String review_text, int review_rating, int user_id, int community_recipe_id, int user_recipe_id) {
        this.review_text = review_text;
        this.review_rating = review_rating;
        this.user_id = user_id;
        this.community_recipe_id = community_recipe_id;
        this.user_recipe_id = user_recipe_id;
    }

    public ReviewModel() {

    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public int getReview_rating() {
        return review_rating;
    }

    public void setReview_rating(int review_rating) {
        this.review_rating = review_rating;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCommunity_recipe_id() {
        return community_recipe_id;
    }

    public void setCommunity_recipe_id(int community_recipe_id) {
        this.community_recipe_id = community_recipe_id;
    }

    public int getUser_recipe_id() {
        return user_recipe_id;
    }

    public String getReview_type() {
        return review_type;
    }

    public void setReview_type(String review_type) {
        this.review_type = review_type;
    }

    public void setUser_recipe_id(int user_recipe_id) {
        this.user_recipe_id = user_recipe_id;
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "review_id=" + review_id +
                ", review_text='" + review_text + '\'' +
                ", review_rating=" + review_rating +
                ", user_id=" + user_id +
                ", community_recipe_id=" + community_recipe_id +
                ", user_recipe_id=" + user_recipe_id +
                '}';
    }

    public void save() {
        // insert to db
        try (DBHandler db = new DBHandler()) {
            db.connect();
            // include review type
            String sql = "INSERT INTO reviews (review_text, review_rating, user_id, community_recipe_id, user_recipe_id, type) VALUES ('" + this.review_text + "', " + this.review_rating + ", " + this.user_id + ", " + this.community_recipe_id + ", " + this.user_recipe_id + ", '" + this.review_type + "');";
            db.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ReviewModel> getAllByTypeAndId(String type, int id) {
        String idColumn = "";
        if(type.equals("community")) {
            idColumn = "community_recipe_id";
        } else if(type.equals("user")) {
            idColumn = "user_recipe_id";
        } else {
            return null;
        }

        ArrayList<ReviewModel> reviews = new ArrayList<>();

        try(DBHandler db = new DBHandler()) {
            db.connect();
            String sql = "SELECT * FROM reviews WHERE " + idColumn + " = " + id + " AND type='" + type  + "';";
            ResultSet rs = db.executeQuery(sql);

            while(rs.next()) {
                ReviewModel review = new ReviewModel();
                review.setReview_id(rs.getInt("review_id"));
                review.setReview_text(rs.getString("review_text"));
                review.setReview_rating(rs.getInt("review_rating"));
                review.setUser_id(rs.getInt("user_id"));
                review.setCommunity_recipe_id(rs.getInt("community_recipe_id"));
                review.setUser_recipe_id(rs.getInt("user_recipe_id"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviews;
    }

    public static ReviewModel findById(int id) {
        ReviewModel review = new ReviewModel();
        try (DBHandler db = new DBHandler()) {
            db.connect();
            String sql = "SELECT * FROM reviews WHERE review_id = " + id + ";";
            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {
                review.setReview_id(rs.getInt("review_id"));
                review.setReview_text(rs.getString("review_text"));
                review.setReview_rating(rs.getInt("review_rating"));
                review.setUser_id(rs.getInt("user_id"));
                review.setCommunity_recipe_id(rs.getInt("community_recipe_id"));
                review.setUser_recipe_id(rs.getInt("user_recipe_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return review;
    }

    public static void main(String[] args) {
        // test find by id
        ReviewModel reviewModel = ReviewModel.findById(1);
        System.out.println(reviewModel);
    }
}
