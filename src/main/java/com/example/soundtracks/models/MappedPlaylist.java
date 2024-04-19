package com.example.soundtracks.models;

import com.example.soundtracks.generated.types.Playlist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MappedPlaylist extends Playlist {

    @JsonSetter("tracks")
    public void mapTracks(JsonNode tracks) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode items = tracks.get("items");

        if (items != null) {
            TrackCollection trackList = mapper.readValue(tracks.traverse(), new TypeReference<TrackCollection>() {
            });
            this.setTracks(trackList.getTracks());
        }
    }
}
