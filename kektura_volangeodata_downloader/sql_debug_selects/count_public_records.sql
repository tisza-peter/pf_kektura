select count(*), 'agency' as tablename from gtfs_public.agency
union
select count(*), 'calendar' as tablename from gtfs_public.calendar
union
select count(*), 'calendar_dates' as tablename from gtfs_public.calendar_dates
union
select count(*), 'feed_info' as tablename from gtfs_public.feed_info
union
select count(*), 'routes' as tablename from gtfs_public.routes
union
select count(*), 'shapes' as tablename from gtfs_public.shapes
union
select count(*), 'stop_times' as tablename from gtfs_public.stop_times
union
select count(*), 'stops' as tablename from gtfs_public.stops
union
select count(*), 'trips' as tablename from gtfs_public.trips
union
select count(*), 'shape_route' as tablename from geom_public.shape_route
union
select count(*), 'shape_stop' as tablename from geom_public.shape_stop
