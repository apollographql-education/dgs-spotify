package com.example.soundtracks.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.io.IOException;

import com.example.soundtracks.generated.types.Track;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackCollection {
    List<Track> tracks;

    public List<Track> getTracks() {
        return this.tracks;
    }

    @JsonSetter("items")
    public void setTracks(JsonNode trackItems) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<MappedTrack> trackList = mapper.readValue(trackItems.traverse(), new TypeReference<>() {});
        this.tracks = trackList.stream().map(MappedTrack::getTrack).toList();
    }
}