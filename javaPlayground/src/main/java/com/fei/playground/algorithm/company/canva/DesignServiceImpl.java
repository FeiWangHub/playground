package com.fei.playground.algorithm.company.canva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

class DesignServiceImpl implements DesignService{

    //1 to many
    HashMap<String, HashMap<String, String>> userID2ContentList = new HashMap<>();
    //share map
    HashMap<String, List<String>> userId2sharedDesignID = new HashMap<>();

    @Override
    public String createDesign(AuthContext ctx, String designContent) {
        String uuid = UUID.randomUUID().toString();
        if(!userID2ContentList.containsKey(ctx.getUserId())){
            HashMap<String, String> id2content = new HashMap<>(1);
            id2content.put(uuid, designContent);
            userID2ContentList.put(ctx.userId, id2content);
        }else{
            userID2ContentList.get(ctx.userId).put(uuid, designContent);
        }

        return uuid;
    }

    @Override
    public String getDesign(AuthContext ctx, String designId) {
        if(!userID2ContentList.containsKey(ctx.userId)){
            return null;
        }

        return userID2ContentList.get(ctx.userId).get(designId);
    }

    @Override
    public List<String> findDesigns(AuthContext ctx) {
        if(!userID2ContentList.containsKey(ctx.userId)){
            return new ArrayList<>(0);
        }

        //userId2sharedDesignID of ctx.user
        HashMap<String, String> uuid2content = userID2ContentList.get(ctx.userId);
        ArrayList<String> result = new ArrayList<>(uuid2content.keySet());
        return result;
    }

    @Override
    public void shareDesign(AuthContext ctx, String designId, String targetUserId) {
        if(!userID2ContentList.containsKey(ctx.userId)
            || !userID2ContentList.get(ctx.userId).containsKey(designId)) return;

        if(!userId2sharedDesignID.containsKey(targetUserId)){
            ArrayList<String> sharedIDs = new ArrayList<>(1);
            sharedIDs.add(designId);
            userId2sharedDesignID.put(targetUserId, sharedIDs);
        }else{
            userId2sharedDesignID.get(targetUserId).add(designId);
        }
    }

    public static void main(String[] args) {

    }
}

