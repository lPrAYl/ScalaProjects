 val tuples = List((1, 20), (2, 20), (1, 30), (2, 40), (2, 60))
tuples.groupMap(_._2)(_._1).view.mapValues(vs => vs.sum / vs.size).toList


