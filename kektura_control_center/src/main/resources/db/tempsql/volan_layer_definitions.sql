--SELECT * FROM volan.shape_stop LIMIT 100
--SELECT * FROM volan.shape_route LIMIT 100
--SELECT * FROM gtfs.agency LIMIT 100
--SELECT * FROM gtfs.calendar LIMIT 100
--SELECT * FROM gtfs.calendardate LIMIT 100
--SELECT * FROM gtfs.feedinfo LIMIT 100
--SELECT * FROM gtfs.route LIMIT 100
--SELECT * FROM gtfs.shape LIMIT 100
--SELECT * FROM gtfs.stop LIMIT 100
--SELECT * FROM gtfs.stoptime LIMIT 100
--SELECT * FROM gtfs.trip LIMIT 100

--unique_stops
SELECT stop.stop_id AS id, stop_name, parent_station, stop_geometry AS geom FROM gtfs.stop INNER JOIN volan.shape_stop ON stop.stop_id = shape_stop.stop_id WHERE stop.location_type=0;

--united_stops
SELECT stop.stop_id AS id, stop_name, stop_geometry AS geom FROM gtfs.stop INNER JOIN volan.shape_stop ON stop.stop_id = shape_stop.stop_id WHERE stop.location_type=1;

--routes
SELECT trip.trip_id AS id, route_long_name AS route_name, shape_route.shape_geometry as geom FROM gtfs.route
INNER JOIN gtfs.trip ON route.route_id=trip.route_id
INNER JOIN volan.shape_route ON shape_route.shape_id=gtfs.trip.shape_id
ORDER BY route.route_id, trip.trip_id LIMIT 100

